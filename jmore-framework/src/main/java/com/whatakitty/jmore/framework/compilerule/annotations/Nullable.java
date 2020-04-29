package com.whatakitty.jmore.framework.compilerule.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * the field annotated may be {null} and the ide should check
 * the caller is being checked the {null} value
 *
 * @author WhatAKitty
 * @date 2019年05月02日
 * @description
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface Nullable {
}
