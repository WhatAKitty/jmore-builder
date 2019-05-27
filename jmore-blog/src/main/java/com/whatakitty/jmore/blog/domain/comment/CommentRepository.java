package com.whatakitty.jmore.blog.domain.comment;

/**
 * comment repository
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public interface CommentRepository {

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
