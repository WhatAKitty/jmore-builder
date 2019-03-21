package com.whatakitty.jmore.lock;

/**
 * lock factory
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
public interface LockFactory {

    /**
     * create lock with lock options
     *
     * @param lockOptions some configuration
     * @return the lock
     */
    Lock create(LockOptions lockOptions);

}
