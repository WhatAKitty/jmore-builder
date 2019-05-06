package com.whatakitty.jmore.framework.validation.rule;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check the value is china idcard
 *
 * Notice: if the target is empty string or null; will be passed.
 *
 * @author WhatAKitty
 * @date 2019/05/06
 * @description
 **/
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(ChinaIDCard.List.class)
@Documented
@Constraint(validatedBy = {})
public @interface ChinaIDCard {

    String message() default "{javax.validation.constraints.ChinaIDCard.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@link ChinaIDCard} annotations on the same element.
     *
     * @see com.whatakitty.jmore.framework.validation.rule.ChinaIDCard
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        ChinaIDCard[] value();

    }

}
