package com.whatakitty.jmore.lock;

import java.util.concurrent.TimeUnit;

/**
 * the lock
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
public interface Lock {

    /**
     * sync lock operation
     */
    void lock() throws LockException;

    /**
     * try to lock if possible
     *
     * @return {true} locked successfully; {false} locked failed.
     */
    boolean tryLock();

    /**
     * try to lock in the specific time
     *
     * @param time the time to wait
     * @param unit time unit
     * @return {true} locked successfully; {false} locked failed.
     */
    boolean lock(long time, TimeUnit unit) throws TimeoutLockException;

    /**
     * unlock
     */
    void unLock() throws BizTimeoutLockException;

}
