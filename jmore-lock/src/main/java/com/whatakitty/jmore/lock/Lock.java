package com.whatakitty.jmore.lock;

import com.whatakitty.jmore.lock.exception.LockException;
import com.whatakitty.jmore.lock.exception.TimeoutLockException;
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
     *
     * @throws LockException
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
     * @throws TimeoutLockException
     * @throws LockException
     */
    void lock(long time, TimeUnit unit) throws LockException;

    /**
     * unlock
     *
     * @throws LockException
     */
    void unLock() throws LockException;

}
