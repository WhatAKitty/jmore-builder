package com.whatakitty.jmore.blog.domain.article;

import com.whatakitty.jmore.blog.domain.resource.Resource;
import com.whatakitty.jmore.blog.domain.resource.ResourceRepository;
import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.blog.domain.service.AggregateIdService;
import com.whatakitty.jmore.blog.domain.type.Type;
import com.whatakitty.jmore.blog.domain.type.TypeRepository;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * article factory
 *
 * @author WhatAKitty
 * @date 2019/05/26
 * @description
 **/
public final class ArticleFactory {

    public static final ArticleFactory FACTORY = new ArticleFactory();

    /**
     * create a new article
     *
     * @param author    the author
     * @param tags      the tags that marked the article
     * @param types     the types the article belongs
     * @param resources like pictures, videos linked with this article
     * @param content   the article's content
     * @param title     the article's title
     * @return article instance
     */
    public Article newArticle(AggregateId<Long> articleId,
                              User author,
                              List<String> tags,
                              List<String> types,
                              List<String> resources,
                              String content,
                              String title,
                              TypeRepository typeRepository,
                              ResourceRepository resourceRepository) {
        // check params
        if (tags == null) {
            tags = new ArrayList<>(0);
        }
        if (types == null) {
            types = new ArrayList<>(0);
        }
        if (resources == null) {
            resources = new ArrayList<>(0);
        }
        if (StringUtils.isBlank(content)) {
            throw new IllegalArgumentException("content should not be null or empty");
        }
        if (StringUtils.isBlank(title)) {
            throw new IllegalArgumentException("title should not be null or empty");
        }

        Article article = new Article(articleId);
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(author);
        article.setTags(tags.parallelStream()
            .map(ArticleTag::of)
            .collect(Collectors.toList()));
        article.setTypes(types.parallelStream()
            .map(typeId -> new Type(typeRepository.nextId()))
            .collect(Collectors.toList()));
        article.setResources(resources.parallelStream()
            .map(resourceId -> new Resource(resourceRepository.nextId()))
            .collect(Collectors.toList()));
        return article;
    }

    /**
     * create a new article
     *
     * @param author    the author
     * @param tags      the tags that marked the article
     * @param types     the types the article belongs
     * @param resources like pictures, videos linked with this article
     * @param content   the article's content
     * @param title     the article's title
     * @return article instance
     */
    public Article newArticleWithDomains(AggregateId<Long> articleId,
                                         User author,
                                         List<String> tags,
                                         List<Type> types,
                                         List<Resource> resources,
                                         String content,
                                         String title) {
        Article article = new Article(articleId);
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(author);
        article.setTags(tags.parallelStream()
            .map(ArticleTag::of)
            .collect(Collectors.toList()));
        article.setTypes(types);
        article.setResources(resources);
        return article;
    }

}
