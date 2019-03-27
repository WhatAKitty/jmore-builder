package com.whatakitty.jmore.lock.redis;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.whatakitty.jmore.lock.Lock;
import com.whatakitty.jmore.lock.exception.LockException;
import com.whatakitty.jmore.lock.exception.TimeoutLockException;
import java.util.concurrent.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
public class RedisLock implements Lock {

    private static final String LOCK_OBJ = "";
    private static final TimeLimiter timeLimiter;

    private final RedisLockOptions lockOptions;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void lock() throws LockException {
        int i = 0;
        ValueOperations<String, Object> op = redisTemplate.opsForValue();
        while (++i < lockOptions.getRetries()) {
            final Boolean locked;
            try {
                locked = op.setIfAbsent(lockOptions.getKey(), LOCK_OBJ, lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS);
            } catch (Throwable t) {
                log.error("something wrong happened", t);

                // sleep
                try {
                    Thread.sleep(lockOptions.getRetriesInterval());
                } catch (InterruptedException e) {
                    // no throw interrupted exception
                    // continue to do lock action instead
                    log.error("sleep interrupted", e);
                    Thread.currentThread().interrupt();
                }

                // continue to try lock action after sleeping a while
                continue;
            }
            if (locked == null) {
                log.error("not supported pipeline or transaction");
                throw new LockException("not supported pipeline or transaction");
            }
            // locked with set successfully
            if (locked) {
                return;
            }

            // sleep
            try {
                Thread.sleep(lockOptions.getRetriesInterval());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        throw new LockException("retries to get lock has been exceed");
    }

    @Override
    public boolean tryLock() {
        ValueOperations<String, Object> op = redisTemplate.opsForValue();
        final Boolean locked;
        try {
            locked = op.setIfAbsent(lockOptions.getKey(), LOCK_OBJ, lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS);
        } catch (Throwable t) {
            return false;
        }
        return locked != null && locked;
    }

    @Override
    public void lock(long time, TimeUnit unit) throws LockException {
        long start = System.currentTimeMillis();
        ValueOperations<String, Object> op = redisTemplate.opsForValue();
        while ((System.currentTimeMillis() - start) < time) {
            final Boolean locked;
            try {
                // wrap setIfAbsent operation with timeout monitor
                // throw timeout exception while execute timeout
                locked = timeLimiter.callUninterruptiblyWithTimeout(
                    () -> op.setIfAbsent(
                        lockOptions.getKey(),
                        LOCK_OBJ,
                        lockOptions.getExpiredAfterSet(),
                        TimeUnit.MILLISECONDS
                    ),
                    // get the min value of remain time and origin time.
                    // prevents negative number of
                    // getting 1ms for remain timeout value if {System.currentTimeMillis() - start}
                    // get negative result
                    Math.min(Math.max(System.currentTimeMillis() - start, 1L), time),
                    unit
                );
            } catch (TimeoutException e) {
                // setIfAbsent command execute timeout
                log.error("lock once with timeout {}ms reached", time);
                throw new TimeoutLockException(time, unit);
            } catch (Throwable t) {
                log.error("something wrong happened", t);

                // sleep
                try {
                    Thread.sleep(lockOptions.getRetriesInterval());
                } catch (InterruptedException e) {
                    log.error("sleep interrupted", e);
                    Thread.currentThread().interrupt();
                }

                continue;
            }
            if (locked == null) {
                log.error("not supported pipeline or transaction");
                throw new LockException("not supported pipeline or transaction");
            }
            if (locked) {
                return;
            }
        }

        log.error("all lock retries with timeout {}ms reached", time);
        throw new TimeoutLockException(time, unit);
    }

    @Override
    public void unLock() throws LockException {
        final Boolean removed;
        try {
            removed = redisTemplate.delete(lockOptions.getKey());
        } catch (Throwable t) {
            throw new LockException("execute delete key in redis get exception", t);
        }

        if (removed == null) {
            log.error("not supported pipeline or transaction");
            throw new LockException("not supported pipeline or transaction");
        }
        if (!removed) {
            throw new LockException("execute delete key in redis failed");
        }
    }

    static {
        // execute policy: min 5, queue limit 100 task, max 10; maximum wait time for idle thread
        // that not in core scope.
        ExecutorService executorService = new ThreadPoolExecutor(5, 10,
            10L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            new ThreadPoolExecutor.DiscardPolicy());

        timeLimiter = SimpleTimeLimiter.create(executorService);
    }

}
