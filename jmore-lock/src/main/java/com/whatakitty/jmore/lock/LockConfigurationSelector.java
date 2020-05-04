package com.whatakitty.jmore.lock;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * lock configuration selector
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
@RequiredArgsConstructor
public final class LockConfigurationSelector implements ImportSelector {

    private final Environment environment;

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        final LockTypeEnum lockTypeEnum = environment.getProperty("spring.lock.lock-type", LockTypeEnum.class);
        switch (lockTypeEnum) {
            case REDIS:
                return new String[] {"com.whatakitty.jmore.lock.redis.RedisLockConfiguration"};
            case ZOOKEEPER:
                return new String[] {"com.whatakitty.jmore.lock.zookeeper.ZookeeperLockConfiguration"};
            default:
                throw new UnsupportedOperationException();
        }
    }

}
