package com.whatakitty.jmore.blog.domain.config;

/**
 * config repository
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public interface ConfigRepository {

    /**
     * create a config
     *
     * @param config
     */
    void create(Config config);

}
