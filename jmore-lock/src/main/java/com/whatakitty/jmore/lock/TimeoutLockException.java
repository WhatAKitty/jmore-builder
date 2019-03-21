package com.whatakitty.jmore.lock;

import java.util.concurrent.TimeUnit;

/**
 * timeout lock exception
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
public class TimeoutLockException extends LockException {

    public TimeoutLockException(long time, TimeUnit unit) {
        super(String.format("To get the lock has been timeout, exceed %s ms.", unit.toMillis(time)));
    }

}
