package com.whatakitty.jmore.framework.validation.rule;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check the value is china phone
 *
 * Notice: if the target is empty string or null; will be passed.
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(ChinaPhone.List.class)
@Documented
@Constraint(validatedBy = {})
public @interface ChinaPhone {

    /**
     * the phone type
     * the any of phone type matched will be fine
     *
     * @return mainland/hong kong/taiwan
     */
    PhoneType[] phoneType() default {PhoneType.MAINLAND};

    String message() default "{javax.validation.constraints.ChinaPhone.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@link ChinaPhone} annotations on the same element.
     *
     * @see com.whatakitty.jmore.framework.validation.rule.ChinaPhone
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        ChinaPhone[] value();
    }

    /**
     * The phone type
     */
    enum PhoneType {
        /**
         * the china mainland
         */
        MAINLAND,
        /**
         * Hong Kong
         */
        HONG_KONG,
        /**
         * Tai wAN
         */
        TAIWAN
    }

}
