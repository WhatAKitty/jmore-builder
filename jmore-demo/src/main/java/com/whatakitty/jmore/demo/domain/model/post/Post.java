package com.whatakitty.jmore.demo.domain.model.post;

import lombok.Builder;

/**
 * post model
 *
 * @author WhatAKitty
 * @date 2019/02/27
 * @description
 **/
@Builder
public class Post {

    private String postId;
    private String title;
    private String content;

}
