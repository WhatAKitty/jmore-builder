package com.whatakitty.jmore.blog.domain.resource;

/**
 * upload failed
 *
 * @author WhatAKitty
 * @date 2019/05/24
 * @description
 **/
public final class UploadFailedException extends RuntimeException {

    public UploadFailedException() {
        super();
    }

    public UploadFailedException(String message) {
        super(message);
    }

    public UploadFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadFailedException(Throwable cause) {
        super(cause);
    }

    protected UploadFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
