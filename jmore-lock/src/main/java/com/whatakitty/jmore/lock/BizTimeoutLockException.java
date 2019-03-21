package com.whatakitty.jmore.lock;

/**
 * biz timeout lock exception
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
public class BizTimeoutLockException extends Exception {

    public BizTimeoutLockException(long time) {
        super(String.format("The business should be done in %s ms, but exceed, please do it by your self", time));
    }

}
