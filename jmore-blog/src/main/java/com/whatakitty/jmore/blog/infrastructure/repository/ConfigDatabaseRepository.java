package com.whatakitty.jmore.blog.infrastructure.repository;

import com.whatakitty.jmore.blog.domain.config.Config;
import com.whatakitty.jmore.blog.domain.config.ConfigRepository;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import org.springframework.stereotype.Component;

/**
 * config database repository
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@Component
public class ConfigDatabaseRepository implements ConfigRepository {
    @Override
    public AggregateId<Long> nextId() {
        return null;
    }

    @Override
    public void create(Config config) {

    }
}
