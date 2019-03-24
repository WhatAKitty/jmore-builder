package com.whatakitty.jmore.lock;

import lombok.Getter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * lock properties configuration
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
@Getter
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConfigurationProperties(prefix = "spring.lock")
public class LockProperties {

    /**
     * the lock type; the jvm lock is default.
     *
     * @return lock type
     */
    private LockTypeEnum lockType;

    /**
     * the redis properties
     */
    private RedisProperties redis;

}
