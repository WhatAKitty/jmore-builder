package com.whatakitty.jmore.blog.domain.security;

/**
 * user repository
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public interface UserRepository {

    /**
     * add user into repository
     *
     * @param user
     */
    void add(User user);

    /**
     * remove the user from repository
     *
     * @param user
     */
    void remove(User user);

}
