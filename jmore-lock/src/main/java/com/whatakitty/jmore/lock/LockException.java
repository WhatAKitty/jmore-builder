package com.whatakitty.jmore.lock;

/**
 * lock exception
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
public class LockException extends Exception {

    public LockException() {
    }

    public LockException(String message) {
        super(message);
    }

    public LockException(Throwable cause) {
        super(cause);
    }
}
