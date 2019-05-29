package com.whatakitty.jmore.blog.domain.service;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;

/**
 * aggregate id service
 *
 * @author WhatAKitty
 * @date 2019/05/30
 * @description
 **/
public final class AggregateIdService {

    public static final AggregateIdService SERVICE = new AggregateIdService();

    /**
     * generate an random string id
     *
     * @param key the key
     * @return aggregate id
     */
    public AggregateId<String> randomStringId(String key) {
        return AggregateId.of(Base64.encodeBase64String(
            (System.currentTimeMillis() + key).getBytes(StandardCharsets.UTF_8)));
    }

    private AggregateIdService() {}

}
