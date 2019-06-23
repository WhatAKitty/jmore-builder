package com.whatakitty.jmore.blog.domain.comment;

import com.whatakitty.jmore.framework.ddd.domain.ValueObject;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import lombok.Value;

/**
 * commentable resource
 *
 * @author WhatAKitty
 * @date 2019/05/29
 * @description
 **/
@Value(staticConstructor = "of")
public class CommentableResource extends ValueObject {

    private AggregateId<?> id;

}
