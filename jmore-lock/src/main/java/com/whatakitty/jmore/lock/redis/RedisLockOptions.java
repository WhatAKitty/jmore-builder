package com.whatakitty.jmore.lock.redis;

import com.whatakitty.jmore.lock.LockOptions;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * redis lock options
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RedisLockOptions extends LockOptions {

    private final long expiredAfterSet;
    private int retries = 3;
    private long retriesInterval = 300;

    public RedisLockOptions(String key, long expiredAfterSet) {
        super(key);
        this.expiredAfterSet = expiredAfterSet;
    }

}
