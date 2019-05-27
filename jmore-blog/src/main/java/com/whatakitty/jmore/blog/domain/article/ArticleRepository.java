package com.whatakitty.jmore.blog.domain.article;

/**
 * article repository
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public interface ArticleRepository {

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
