package com.whatakitty.jmore.demo.infrastructure.repository.mysql;

import com.whatakitty.jmore.demo.domain.model.user.User;
import com.whatakitty.jmore.demo.domain.repository.IUserRepository;
import com.whatakitty.jmore.demo.infrastructure.dao.entity.UserDO;
import com.whatakitty.jmore.demo.infrastructure.dao.mysql.UserMapper;
import com.whatakitty.jmore.demo.infrastructure.repository.converters.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

/**
 * mysql user repository
 *
 * @author WhatAKitty
 * @date 2019/02/28
 * @description
 **/
@RequiredArgsConstructor
@Repository
public class MySqlUserRepository implements IUserRepository {

    private final UserMapper userMapper;
    private final UserConverter userConverter;

    @Override
    public boolean login(User user) {
        return false;
    }

    @Override
    public User query(String username) {
        Example example = Example.builder(UserDO.class).build();
        example.and().andEqualTo("username", username);
        UserDO userDO = userMapper.selectOneByExample(example);
        return userConverter.fromDO(userDO);
    }


}
