package com.whatakitty.jmore.blog.application.article;

import com.whatakitty.jmore.blog.domain.article.Article;
import com.whatakitty.jmore.blog.domain.article.ArticleFactory;
import com.whatakitty.jmore.blog.domain.article.ArticleRepository;
import com.whatakitty.jmore.blog.domain.resource.Resource;
import com.whatakitty.jmore.blog.domain.resource.ResourceRepository;
import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.blog.domain.security.UserRepository;
import com.whatakitty.jmore.blog.domain.type.Type;
import com.whatakitty.jmore.blog.domain.type.TypeRepository;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * article service
 *
 * @author WhatAKitty
 * @date 2019/05/30
 * @description
 **/
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final TypeRepository typeRepository;
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void draft(ArticleDTO articleDTO) {
        final User currentUser = userRepository.currentUser();
        final AggregateId<Long> articleId = articleRepository.nextId();
        final Article article = ArticleFactory.FACTORY.newArticle(
            articleId,
            currentUser,
            articleDTO.getTags(),
            articleDTO.getTypes(),
            articleDTO.getResources(),
            articleDTO.getContent(),
            articleDTO.getTitle(),
            typeRepository,
            resourceRepository
        );
        article.draft();
        articleRepository.add(article);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void post(Long articleId) {
        final Article article = articleRepository.at(AggregateId.of(articleId));
        article.publish();
        articleRepository.update(article);
    }

    /**
     * post by the current user
     *
     * @param articleDTO the article info
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void saveAndPost(ArticleDTO articleDTO) {
        final User currentUser = userRepository.currentUser();
        final AggregateId<Long> articleId = articleRepository.nextId();
        final Article article = ArticleFactory.FACTORY.newArticle(
            articleId,
            currentUser,
            articleDTO.getTags(),
            articleDTO.getTypes(),
            articleDTO.getResources(),
            articleDTO.getContent(),
            articleDTO.getTitle(),
            typeRepository,
            resourceRepository
        );
        article.publish();
        articleRepository.add(article);
    }

    /**
     * modify the post
     *
     * @param articleDTO article info
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void modify(ArticleDTO articleDTO) {
        final Article article = articleRepository.at(AggregateId.of(articleDTO.getId()));
        article.modifyTitle(articleDTO.getTitle());
        article.modifyContent(
            articleDTO.getContent(),
            articleDTO.getResources().parallelStream()
                .map(resourceId -> new Resource(resourceRepository.nextId()))
                .collect(Collectors.toList())
        );
        article.changeArticleTag(articleDTO.getTags());
        article.changeType(
            articleDTO.getTypes().parallelStream()
                .map(typeId -> new Type(typeRepository.nextId()))
                .collect(Collectors.toList())
        );
        articleRepository.update(article);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void drop(Long articleId) {
        final Article article = articleRepository.at(AggregateId.of(articleId));
        article.dropped();
        articleRepository.remove(article);
    }

}
