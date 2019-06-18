package com.whatakitty.jmore.blog.domain.security;

import com.whatakitty.jmore.framework.ddd.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * user type
 * 0. guest
 * 1. author
 *
 * @author WhatAKitty
 * @date 2019/05/24
 * @description
 **/
@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
public final class UserType extends ValueObject {

    /**
     * guest
     */
    public static UserType GUEST = UserType.of(Type.GUEST);
    /**
     * author
     */
    public static UserType AUTHOR = UserType.of(Type.AUTHOR);

    private final Type type;

    @RequiredArgsConstructor
    public enum Type {

        /**
         * guest
         */
        GUEST(0),
        /**
         * author
         */
        AUTHOR(1);

        @Getter
        private final int value;

    }

}
