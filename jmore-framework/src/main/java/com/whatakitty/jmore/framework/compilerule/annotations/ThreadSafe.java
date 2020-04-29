package com.whatakitty.jmore.framework.compilerule.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * the type or field annotated should be thread safe
 *
 * @author WhatAKitty
 * @date 2019年05月02日
 * @description
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
public @interface ThreadSafe {
}
