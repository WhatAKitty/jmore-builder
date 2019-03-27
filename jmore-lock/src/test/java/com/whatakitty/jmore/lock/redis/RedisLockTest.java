package com.whatakitty.jmore.lock.redis;

import static org.mockito.Mockito.when;

import com.whatakitty.jmore.lock.exception.LockException;
import com.whatakitty.jmore.lock.exception.TimeoutLockException;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * redis lock unit test
 *
 * @author WhatAKitty
 * @date 2019/03/24
 * @description
 **/
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class RedisLockTest {

    @Mock
    private RedisTemplate<String, Object> redisTemplate;
    @Mock
    private ValueOperations<String, Object> op;
    private RedisLock redisLock;
    private RedisLockOptions lockOptions;

    @Before
    public void setup() {
        when(redisTemplate.opsForValue()).thenReturn(op);

        lockOptions = new RedisLockOptions("lock_key", 10 * 60 * 1000);
        redisLock = new RedisLock(lockOptions, redisTemplate);
    }

    @Test
    public void test_lock_normal() {
        when(op.setIfAbsent(lockOptions.getKey(), "", lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS))
            .thenReturn(true);
        try {
            redisLock.lock();
        } catch (LockException e) {
            Assert.fail();
        }
    }

    @Test
    public void test_lock_tries_exceed() {
        final long old = lockOptions.getRetriesInterval();
        lockOptions.setRetriesInterval(1000);

        when(op.setIfAbsent(lockOptions.getKey(), "", lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS))
            .thenReturn(false);

        try {
            redisLock.lock();
            Assert.fail();
        } catch (LockException e) {
            // ignore
        } finally {
            lockOptions.setRetriesInterval(old);
        }
    }

    @Test
    public void test_lock_redis_failed() {
        when(op.setIfAbsent(lockOptions.getKey(), "", lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS))
            .thenReturn(null);

        try {
            redisLock.lock();
            Assert.fail();
        } catch (LockException e) {
            // ignore
        }
    }

    @Test
    public void test_lock_redis_exception() {
        when(op.setIfAbsent(lockOptions.getKey(), "", lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS))
            .thenThrow(new RedisConnectionFailureException("connection failed"));

        try {
            redisLock.lock();
            Assert.fail();
        } catch (LockException e) {
            // ignore
        }
    }

    @Test
    public void test_tryLock_normal() {
        when(op.setIfAbsent(lockOptions.getKey(), "", lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS))
            .thenReturn(true);

        Assert.assertTrue(redisLock.tryLock());
    }

    @Test
    public void test_tryLock_redis_failed() {
        when(op.setIfAbsent(lockOptions.getKey(), "", lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS))
            .thenReturn(null);

        Assert.assertFalse(redisLock.tryLock());

        when(op.setIfAbsent(lockOptions.getKey(), "", lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS))
            .thenReturn(false);

        Assert.assertFalse(redisLock.tryLock());
    }

    @Test
    public void test_tryLock_redis_exception() {
        when(op.setIfAbsent(lockOptions.getKey(), "", lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS))
            .thenThrow(new RedisConnectionFailureException("connection failed"));

        Assert.assertFalse(redisLock.tryLock());
    }

    @Test
    public void test_lockWithTimeout_normal() {
        when(op.setIfAbsent(lockOptions.getKey(), "", lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS))
            .thenReturn(true);

        try {
            redisLock.lock(10000, TimeUnit.MILLISECONDS);
        } catch (LockException e) {
            log.error("lock with timeout normal", e);
            Assert.fail();
        }
    }

    @Test
    public void test_lockWithTimeout_exceed() {
        when(op.setIfAbsent(lockOptions.getKey(), "", lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS))
            .thenAnswer(res -> {
                Thread.sleep(3000);
                return true;
            });

        try {
            redisLock.lock(1000, TimeUnit.MILLISECONDS);
            Assert.fail();
        } catch (TimeoutLockException e) {
            // ignore
        } catch (LockException e) {
            Assert.fail();
        }
    }

    @Test
    public void test_lockWithTimeout_failed() {
        when(op.setIfAbsent(lockOptions.getKey(), "", lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS))
            .thenReturn(false);

        try {
            redisLock.lock(10000, TimeUnit.MILLISECONDS);
            Assert.fail();
        } catch (TimeoutLockException e) {
            // ignore
        } catch (LockException e) {
            Assert.fail();
        }
    }

    @Test
    public void test_lockWithTimeout_redis_exception() {
        when(op.setIfAbsent(lockOptions.getKey(), "", lockOptions.getExpiredAfterSet(), TimeUnit.MILLISECONDS))
            .thenThrow(new RedisConnectionFailureException("connection failed"));

        try {
            redisLock.lock(10000, TimeUnit.MILLISECONDS);
            Assert.fail();
        } catch (TimeoutLockException e) {
            // ignore
        } catch (LockException e) {
            Assert.fail();
        }
    }

    @Test
    public void unLock() {
    }

}