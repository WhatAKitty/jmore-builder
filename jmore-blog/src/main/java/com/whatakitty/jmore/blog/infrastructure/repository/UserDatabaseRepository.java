package com.whatakitty.jmore.blog.infrastructure.repository;

import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.blog.domain.security.UserRepository;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.user.UserDO;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.user.UserMapper;
import com.whatakitty.jmore.framework.ddd.domain.AggregateStatus;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * user database repository
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@RequiredArgsConstructor
@Component
public class UserDatabaseRepository implements UserRepository {

    private static final User ANONYMOUS;

    private final UserMapper userMapper;

    @Override
    public AggregateId<Long> nextId() {
        return null;
    }

    @Override
    public User findWithUsername(String username) {
        UserDO userDO = userMapper.selectByUsername(username);

        if (null == userDO) {
            return ANONYMOUS;
        }

        User user = new User(AggregateId.of(userDO.getId()));
        user.setUsername(userDO.getUsername());
        user.setPassword(userDO.getPassword());
        return user;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void remove(User user) {

    }

    static {
        ANONYMOUS = new User(AggregateId.of(-1L));
        ANONYMOUS.setStatus(AggregateStatus.INVALID);
    }

}
