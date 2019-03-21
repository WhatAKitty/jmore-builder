package com.whatakitty.jmore.lock.redis;

import com.whatakitty.jmore.lock.BizTimeoutLockException;
import com.whatakitty.jmore.lock.Lock;
import com.whatakitty.jmore.lock.LockException;
import com.whatakitty.jmore.lock.TimeoutLockException;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * redis lock
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
@SuppressWarnings("Duplicates")
@RequiredArgsConstructor
public class RedisLock implements Lock {

    private static final String LOCK_OBJ = "";
    private final RedisLockOptions lockOptions;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void lock() throws LockException {
        int i = 0;
        ValueOperations<String, Object> op = redisTemplate.opsForValue();
        while (++i < lockOptions.getRetries()) {
            Boolean locked = op.setIfAbsent(lockOptions.getKey(), LOCK_OBJ, lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS);
            if (locked != null && locked) {
                break;
            }

            // sleep
            try {
                Thread.sleep(lockOptions.getRetriesInterval());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        throw new LockException("retries to get lock has been exceed");
    }

    @Override
    public boolean tryLock() {
        ValueOperations<String, Object> op = redisTemplate.opsForValue();
        Boolean locked = op.setIfAbsent(lockOptions.getKey(), LOCK_OBJ, lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS);
        return locked != null && locked;
    }

    @Override
    public boolean lock(long time, TimeUnit unit) throws TimeoutLockException {
        long start = System.currentTimeMillis();
        ValueOperations<String, Object> op = redisTemplate.opsForValue();
        while ((System.currentTimeMillis() - start) < time) {
            Boolean locked = op.setIfAbsent(lockOptions.getKey(), LOCK_OBJ, lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS);
            if (locked != null && locked) {
                break;
            }

            // sleep
            try {
                Thread.sleep(lockOptions.getRetriesInterval());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        throw new TimeoutLockException(time, unit);
    }

    @Override
    public void unLock() throws BizTimeoutLockException {
        Boolean removed = redisTemplate.delete(lockOptions.getKey());
        if (removed == null || !removed) {
            throw new BizTimeoutLockException(lockOptions.getExpiredAfterSet());
        }
    }

}
