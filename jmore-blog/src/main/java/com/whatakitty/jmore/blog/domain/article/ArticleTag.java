package com.whatakitty.jmore.blog.domain.article;

import com.whatakitty.jmore.framework.ddd.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * article tag value object
 *
 * @author WhatAKitty
 * @date 2019/05/23
 * @description
 **/
@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
public class ArticleTag extends ValueObject {

    private String tagName;

}
