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
     * get the next id for article
     *
     * @return
     */
    AggregateId<Long> nextId();

    /**
     * find article from repository by article id
     *
     * @param articleId
     */
    Article at(AggregateId<Long> articleId);

    /**
     * add article into repository
     *
     * @param article
     */
    void add(Article article);

    /**
     * update article
     *
     * @param article
     */
    void update(Article article);

    /**
     * remove article from repository
     *
     * @param article
     */
    void remove(Article article);

}
