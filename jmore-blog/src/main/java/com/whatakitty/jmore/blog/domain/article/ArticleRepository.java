package com.whatakitty.jmore.blog.domain.article;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;

/**
 * article repository
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public interface ArticleRepository {

    /**
     * find article from repository by article id
     *
     * @param articleId
     */
    Article at(AggregateId<String> articleId);

    /**
     * add article into repository
     *
     * @param article
     */
    void add(Article article);

    /**
     * remove article from repository
     *
     * @param article
     */
    void remove(Article article);

}
