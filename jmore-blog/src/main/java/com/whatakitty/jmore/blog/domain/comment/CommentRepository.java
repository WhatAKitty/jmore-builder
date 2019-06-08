package com.whatakitty.jmore.blog.domain.comment;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;

/**
 * comment repository
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public interface CommentRepository {

    /**
     * get the next id for comment
     *
     * @return
     */
    AggregateId<Long> nextId();

    /**
     * add comment
     *
     * @param comment
     */
    void add(Comment comment);

    /**
     * remove the comment from repository
     *
     * @param comment
     */
    void remove(Comment comment);

}
