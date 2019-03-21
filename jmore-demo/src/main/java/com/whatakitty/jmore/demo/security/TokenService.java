package com.whatakitty.jmore.demo.security;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

/**
 * token service
 *
 * @author WhatAKitty
 * @date 2019/02/26
 * @description
 **/
@Component("tokenStore")
public class TokenService extends RedisTokenStore {

    public TokenService(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

}
