package com.whatakitty.jmore.lock.redis;

import com.whatakitty.jmore.lock.LockFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis lock configuration
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 *
 * @see com.whatakitty.jmore.lock.EnableJMoreLock
 * @see com.whatakitty.jmore.lock.LockConfigurationSelector
 **/
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class RedisLockConfiguration {

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redis = new RedisTemplate<>();
        redis.setConnectionFactory(redisConnectionFactory);
        return redis;
    }

    @Bean
    @ConditionalOnBean(LockFactory.class)
    public LockFactory lockFactory(RedisTemplate<String, Object> redisTemplate) {
        return new RedisLockFactory(redisTemplate);
    }

}
