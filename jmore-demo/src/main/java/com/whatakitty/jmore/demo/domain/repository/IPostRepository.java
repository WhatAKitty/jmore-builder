package com.whatakitty.jmore.demo.domain.repository;

import com.whatakitty.jmore.demo.domain.model.post.Post;

/**
 * post repository
 *
 * @author WhatAKitty
 * @date 2019/02/27
 * @description
 **/
public interface IPostRepository {

    /**
     * query post by post id
     *
     * @param postId post unique id
     * @return post info
     */
    Post query(String postId);

}
