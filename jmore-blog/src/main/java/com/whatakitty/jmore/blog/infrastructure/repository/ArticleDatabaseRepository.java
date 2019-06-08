package com.whatakitty.jmore.blog.infrastructure.repository;

import com.whatakitty.jmore.blog.domain.article.Article;
import com.whatakitty.jmore.blog.domain.article.ArticleFactory;
import com.whatakitty.jmore.blog.domain.article.ArticleRepository;
import com.whatakitty.jmore.blog.domain.article.ArticleStatus;
import com.whatakitty.jmore.blog.domain.resource.Resource;
import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.blog.domain.security.UserType;
import com.whatakitty.jmore.blog.domain.type.Type;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.article.ArticleDO;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.article.ArticleMapper;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.articleresource.ArticleResourceDO;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.articleresource.ArticleResourceMapper;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.articletags.ArticleTagsDO;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.articletags.ArticleTagsMapper;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.articletypes.ArticleTypesDO;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.articletypes.ArticleTypesMapper;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.type.TypeDO;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.type.TypeMapper;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.user.UserDO;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.user.UserMapper;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * article repository
 *
 * @author WhatAKitty
 * @date 2019/05/31
 * @description
 **/
@Component
@RequiredArgsConstructor
public final class ArticleDatabaseRepository implements ArticleRepository {

    private final ArticleMapper articleMapper;
    private final TypeMapper typeMapper;
    private final UserMapper userMapper;
    private final ArticleTagsMapper articleTagsMapper;
    private final ArticleTypesMapper articleTypesMapper;
    private final ArticleResourceMapper articleResourceMapper;

    private final IdDatabaseRepository idDatabaseRepository;

    @Override
    public AggregateId<Long> nextId() {
        return AggregateId.of(idDatabaseRepository.nextId());
    }

    @Override
    public Article at(AggregateId<Long> articleId) {
        final ArticleDO articleDO = articleMapper.selectByPrimaryKey(articleId.getId());
        final List<ArticleTagsDO> articleTagDOS = articleTagsMapper.selectListByArticleId(articleId.getId());
        final List<TypeDO> articleTypeDOS = typeMapper.selectListByArticleId(articleId.getId());
        final UserDO userDO = userMapper.selectAllByPrimaryKey(articleDO.getAuthor());

        final User user = new User(AggregateId.of(userDO.getId()));
        user.setUsername(userDO.getUsername());
        user.setNickname(userDO.getNickName());
        user.setUserType(UserType.AUTHOR);
        user.setLastLoginIp(userDO.getLastLoginIpv4());
        user.setLastLoginDate(userDO.getLastLoginDate());

        final List<Type> types = articleTypeDOS.parallelStream()
            .map(typeDO -> {
                Type type = new Type(AggregateId.of(typeDO.getId()));
                type.setTypeName(typeDO.getTypeName());
                return type;
            })
            .collect(Collectors.toList());

        final Article article = ArticleFactory.FACTORY.newArticleWithDomains(
            AggregateId.of(articleDO.getId()),
            user,
            articleTagDOS.parallelStream().map(ArticleTagsDO::getTagName).collect(Collectors.toList()),
            types,
            new ArrayList<>(0),
            articleDO.getContent(),
            articleDO.getTitle()
        );
        article.setPublishDate(articleDO.getPublishDate());
        article.setArticleStatus(ArticleStatus.of(articleDO.getStatus()));
        article.setCreateDate(articleDO.getGmtCreate());
        article.setModifyDate(articleDO.getGmtModified());
        return article;
    }

    @Override
    public void add(Article article) {
        final Date currentDate = new Date();

        // search user id by user code
        final UserDO userDO = userMapper.selectByPrimaryKey(article.getAuthor().getId().getId());

        // persist article
        final ArticleDO articleDO = new ArticleDO();
        articleDO.setAuthor(userDO.getId());
        articleDO.setTitle(article.getTitle());
        articleDO.setContent(article.getContent());
        articleDO.setPublishDate(article.getPublishDate());
        articleDO.setStatus(article.getArticleStatus().getStatus().getValue());
        articleDO.setGmtCreate(currentDate);
        articleDO.setGmtModified(currentDate);
        articleDO.setGmtCreator(userDO.getId());
        articleDO.setGmtModifier(userDO.getId());
        articleMapper.insert(articleDO);

        // persist relation between article and tags
        insertRelationBetweenArticleAndTags(article);

        // persist relation between article and types
        insertRelationBetweenArticleAndTypes(article);

        // persist relation between article and resources
        insertRelationBetweenArticleAndResources(article);
    }

    @Override
    public void update(Article article) {
        final ArticleDO articleDO = new ArticleDO();
        articleDO.setId(article.getId().getId());
        articleMapper.updateByPrimaryKeySelective(articleDO);

        // update relation between article and tags
        deleteRelationBetweenArticleAndTags(article);
        insertRelationBetweenArticleAndTags(article);

        // update relation between article and types
        deleteRelationBetweenArticleAndTypes(article);
        insertRelationBetweenArticleAndTypes(article);

        // update relation between article and resources
        deleteRelationBetweenArticleAndResources(article);
        insertRelationBetweenArticleAndResources(article);

    }

    @Override
    public void remove(Article article) {
        // remove relations between article and resources
        deleteRelationBetweenArticleAndResources(article);
        // remove relations between article and types
        deleteRelationBetweenArticleAndTypes(article);
        // remove relations between article and tags
        deleteRelationBetweenArticleAndTags(article);
        // remove article
        articleMapper.deleteByPrimaryKey(article.getId().getId());
    }

    private void insertRelationBetweenArticleAndTags(Article article) {
        final Long articleId = article.getId().getId();
        final List<ArticleTagsDO> articleTagsDOS = article.getTags().parallelStream()
            .map(item -> {
                final ArticleTagsDO articleTagsDO = new ArticleTagsDO();
                articleTagsDO.setArticleId(articleId);
                articleTagsDO.setTagName(item.getTagName());
                return articleTagsDO;
            })
            .collect(Collectors.toList());
        articleTagsMapper.insertList(articleTagsDOS);
    }

    private void insertRelationBetweenArticleAndTypes(Article article) {
        final Long articleId = article.getId().getId();
        final List<ArticleTypesDO> articleTypesDOS = article.getTypes().parallelStream()
            .map(Type::getId)
            .map(AggregateId::getId)
            .map(typeId -> {
                final ArticleTypesDO articleTypesDO = new ArticleTypesDO();
                articleTypesDO.setArticleId(articleId);
                articleTypesDO.setTypeId(typeId);
                return articleTypesDO;
            })
            .collect(Collectors.toList());
        articleTypesMapper.insertList(articleTypesDOS);
    }

    private void insertRelationBetweenArticleAndResources(Article article) {
        final Long articleId = article.getId().getId();
        final List<ArticleResourceDO> articleResourceDOS = article.getResources().parallelStream()
            .map(Resource::getId)
            .map(AggregateId::getId)
            .map(resourceId -> {
                final ArticleResourceDO articleResourceDO = new ArticleResourceDO();
                articleResourceDO.setArticleId(articleId);
                articleResourceDO.setResourceId(resourceId);
                return articleResourceDO;
            })
            .collect(Collectors.toList());
        articleResourceMapper.insertList(articleResourceDOS);
    }

    private void deleteRelationBetweenArticleAndTags(Article article) {
        articleTagsMapper.deleteAllTagsByArticleId(article.getId().getId());
    }

    private void deleteRelationBetweenArticleAndTypes(Article article) {
        articleTypesMapper.deleteAllByArticleId(article.getId().getId());
    }

    private void deleteRelationBetweenArticleAndResources(Article article) {
        articleResourceMapper.deleteAllByArticleId(article.getId().getId());
    }

}
