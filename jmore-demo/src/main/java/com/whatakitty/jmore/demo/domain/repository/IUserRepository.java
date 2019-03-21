package com.whatakitty.jmore.demo.domain.repository;

import com.whatakitty.jmore.demo.domain.model.user.User;

/**
 * user repository
 *
 * @author WhatAKitty
 * @date 2019/02/28
 * @description
 **/
public interface IUserRepository {

    /**
     * login into app
     *
     * @param user the user info
     * @return {true} login successfully; {false} login failed
     */
    boolean login(User user);

    /**
     * query user by username
     * @param username user name
     * @return user info
     */
    User query(String username);

}
