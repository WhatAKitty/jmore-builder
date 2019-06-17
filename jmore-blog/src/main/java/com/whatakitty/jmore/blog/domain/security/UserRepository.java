package com.whatakitty.jmore.blog.domain.security;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;

/**
 * user repository
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public interface UserRepository {

    /**
     * get the next id for user
     *
     * @return
     */
    AggregateId<Long> nextId();

    /**
     * find an user by username
     *
     * @param username
     * @return
     */
    User findWithUsername(String username);

    /**
     * add user into repository
     *
     * @param user
     */
    void add(User user);

    /**
     * update user into repository
     *
     * @param user
     */
    void update(User user);

    /**
     * remove the user from repository
     *
     * @param user
     */
    void remove(User user);

}
