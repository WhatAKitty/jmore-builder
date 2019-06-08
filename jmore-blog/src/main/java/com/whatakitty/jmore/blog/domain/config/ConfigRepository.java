package com.whatakitty.jmore.blog.domain.config;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;

/**
 * config repository
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public interface ConfigRepository {

    /**
     * get the next id for config
     *
     * @return
     */
    AggregateId<Long> nextId();

    /**
     * create a config
     *
     * @param config
     */
    void create(Config config);

}
