package com.whatakitty.jmore.blog.application.article;

import com.whatakitty.jmore.blog.domain.article.Article;
import com.whatakitty.jmore.blog.domain.article.ArticleFactory;
import com.whatakitty.jmore.blog.domain.article.ArticleRepository;
import com.whatakitty.jmore.blog.domain.resource.Resource;
import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.blog.domain.type.Type;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * article service
 *
 * @author WhatAKitty
 * @date 2019/05/30
 * @description
 **/
@Service
@RequiredArgsConstructor
public final class ArticleService {

    private final ArticleRepository articleRepository;

    public void save(ArticleDTO articleDTO) {
        // TODO get the current user
        final Article article = ArticleFactory.FACTORY.newArticle(
            new User(AggregateId.of(1L)),
            articleDTO.getTags(),
            articleDTO.getTypes(),
            articleDTO.getResources(),
            articleDTO.getContent(),
            articleDTO.getTitle()
        );
        article.draft();
        articleRepository.add(article);
    }

    public void post(ArticleDTO articleDTO) {
        final Article article = articleRepository.at(AggregateId.of(articleDTO.getId()));
        article.publish();
        articleRepository.add(article);
    }

    /**
     * post by the current user
     *
     * @param articleDTO the article info
     */
    public void saveAndPost(ArticleDTO articleDTO) {
        // TODO get the current user
        final Article article = ArticleFactory.FACTORY.newArticle(
            new User(AggregateId.of(1L)),
            articleDTO.getTags(),
            articleDTO.getTypes(),
            articleDTO.getResources(),
            articleDTO.getContent(),
            articleDTO.getTitle()
        );
        article.publish();
        articleRepository.add(article);
    }

    /**
     * modify the post
     *
     * @param articleDTO article info
     */
    public void modify(ArticleDTO articleDTO) {
        final Article article = articleRepository.at(AggregateId.of(articleDTO.getId()));
        article.modifyTitle(articleDTO.getTitle());
        article.modifyContent(
            articleDTO.getContent(),
            articleDTO.getResources().parallelStream()
                .map(resourceId -> new Resource(AggregateId.of(resourceId)))
                .collect(Collectors.toList())
        );
        article.changeArticleTag(articleDTO.getTags());
        article.changeType(
            articleDTO.getTypes().parallelStream()
                .map(typeId -> new Type(AggregateId.of(typeId)))
                .collect(Collectors.toList())
        );
        articleRepository.add(article);
    }

    public void drop(ArticleDTO articleDTO) {
        final Article article = articleRepository.at(AggregateId.of(articleDTO.getId()));
        article.dropped();
        articleRepository.add(article);
    }

}
