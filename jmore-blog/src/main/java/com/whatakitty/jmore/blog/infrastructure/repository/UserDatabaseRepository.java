package com.whatakitty.jmore.blog.infrastructure.repository;

import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.blog.domain.security.UserRepository;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import org.springframework.stereotype.Component;

/**
 * user database repository
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@Component
public class UserDatabaseRepository implements UserRepository {
    @Override
    public AggregateId<Long> nextId() {
        return null;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void remove(User user) {

    }
}
