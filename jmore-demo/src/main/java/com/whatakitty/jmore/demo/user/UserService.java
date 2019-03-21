package com.whatakitty.jmore.demo.user;

import com.whatakitty.jmore.demo.infrastructure.dao.mysql.UserMapper;
import org.springframework.stereotype.Service;

/**
 * user service
 *
 * @author WhatAKitty
 * @date 2019/02/27
 * @description
 **/
@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(final UserMapper userMapper) {
        this.userMapper = userMapper;
    }



}
