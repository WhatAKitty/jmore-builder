package com.whatakitty.jmore.lock.redis;

import com.whatakitty.jmore.lock.Lock;
import com.whatakitty.jmore.lock.LockFactory;
import com.whatakitty.jmore.lock.LockOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

/**
 * redis lock factory
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
@RequiredArgsConstructor
public class RedisLockFactory implements LockFactory {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Lock create(LockOptions lockOptions) {
        Assert.isInstanceOf(RedisLockOptions.class, lockOptions);
        return new RedisLock((RedisLockOptions) lockOptions, redisTemplate);
    }

}
