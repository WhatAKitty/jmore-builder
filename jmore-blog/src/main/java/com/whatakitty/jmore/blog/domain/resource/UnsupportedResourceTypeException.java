package com.whatakitty.jmore.blog.domain.resource;

/**
 * unsupported resource type exception
 *
 * @author WhatAKitty
 * @date 2019/06/24
 * @description
 **/
public class UnsupportedResourceTypeException extends RuntimeException {

    public UnsupportedResourceTypeException() {
        super();
    }

    public UnsupportedResourceTypeException(String message) {
        super(message);
    }

    public UnsupportedResourceTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedResourceTypeException(Throwable cause) {
        super(cause);
    }

    protected UnsupportedResourceTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
