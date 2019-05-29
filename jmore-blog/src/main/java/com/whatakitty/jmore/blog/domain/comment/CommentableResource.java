package com.whatakitty.jmore.blog.domain.comment;

import com.whatakitty.jmore.framework.ddd.domain.ValueObject;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;

/**
 * commentable resource
 *
 * @author WhatAKitty
 * @date 2019/05/29
 * @description
 **/
public class CommentableResource extends ValueObject {

    private AggregateId<Object> id;

}
