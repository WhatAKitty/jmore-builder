package com.whatakitty.jmore.blog.infrastructure.repository.exception;

/**
 * id generation exception
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
public class IdGenerationException extends RuntimeException {

    public IdGenerationException() {
        super();
    }

    public IdGenerationException(String message) {
        super(message);
    }

    public IdGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdGenerationException(Throwable cause) {
        super(cause);
    }

    protected IdGenerationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
