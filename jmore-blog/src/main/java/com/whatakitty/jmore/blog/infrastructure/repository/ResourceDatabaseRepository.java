package com.whatakitty.jmore.blog.infrastructure.repository;

import com.whatakitty.jmore.blog.domain.resource.Resource;
import com.whatakitty.jmore.blog.domain.resource.ResourceRepository;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import org.springframework.stereotype.Component;

/**
 * resource database repository
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@Component
public class ResourceDatabaseRepository implements ResourceRepository {
    @Override
    public AggregateId<Long> nextId() {
        return null;
    }

    @Override
    public void add(Resource resource) {

    }

    @Override
    public void remove(Resource resource) {

    }
}
