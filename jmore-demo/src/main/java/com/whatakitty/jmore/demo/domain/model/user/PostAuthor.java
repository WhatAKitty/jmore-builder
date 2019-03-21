package com.whatakitty.jmore.demo.domain.model.user;

import com.whatakitty.jmore.demo.domain.model.post.Post;

/**
 * post author
 *
 * @author WhatAKitty
 * @date 2019/02/27
 * @description
 **/
public class PostAuthor extends User {

    public PostAuthor(String userId) {
        super(userId);
    }

    /**
     * publish post
     *
     * @param title post title
     * @param content post content
     * @return post instance
     */
    public Post publish(String title, String content) {
        return Post.builder()
            .title(title)
            .content(content)
            .build();
    }

}
