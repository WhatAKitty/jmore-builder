package com.whatakitty.jmore.blog.domain.comment;

/**
 * rubbish detect service
 *
 * @author WhatAKitty
 * @date 2019/05/26
 * @description
 **/
public interface RubbishDetectService {

    /**
     * the content is rubbish
     *
     * @return {true} yes or {false} no
     */
    boolean rubbish(String content);

}
