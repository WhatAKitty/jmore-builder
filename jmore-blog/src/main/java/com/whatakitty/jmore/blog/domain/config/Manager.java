package com.whatakitty.jmore.blog.domain.config;

import com.whatakitty.jmore.framework.ddd.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/05/25
 * @description
 **/
@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
public final class Manager extends ValueObject {

    private final String username;
    private final String password;
    private final String mobile;

}
