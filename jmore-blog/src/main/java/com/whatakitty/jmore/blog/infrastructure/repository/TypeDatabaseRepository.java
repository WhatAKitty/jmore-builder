package com.whatakitty.jmore.blog.infrastructure.repository;

import com.whatakitty.jmore.blog.domain.type.Type;
import com.whatakitty.jmore.blog.domain.type.TypeRepository;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import org.springframework.stereotype.Component;

/**
 * type repository
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@Component
public class TypeDatabaseRepository implements TypeRepository {

    @Override
    public AggregateId<Long> nextId() {
        return null;
    }

    @Override
    public void add(Type type) {

    }

    @Override
    public void remove(Type type) {

    }

}
