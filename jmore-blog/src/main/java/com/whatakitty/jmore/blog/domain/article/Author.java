package com.whatakitty.jmore.blog.domain.article;

import com.whatakitty.jmore.framework.ddd.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * author
 *
 * @author WhatAKitty
 * @date 2019/05/24
 * @description
 **/
@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
public final class Author extends ValueObject {

    private String authorName;

}
