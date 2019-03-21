package com.whatakitty.jmore.demo.domain.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * user
 *
 * @author WhatAKitty
 * @date 2019/02/27
 * @description
 **/
@Getter
@Builder
@NoArgsConstructor
public class User {

    /**
     * user unique id
     */
    private String userId;
    /**
     * user name
     */
    private String name;
    /**
     * user sex
     */
    private UserSex userSex;
    /**
     * user's account info
     */
    private AccountInfo accountInfo;

    public User(String userId) {
        this.userId = userId;
    }

    /**
     * login into app
     *
     * @param username username to login
     * @param password password to login
     * @return user info
     */
    public User login(String username, String password) {
        return User.builder()
            .accountInfo(AccountInfo.of(username, password, false, true, false))
            .build();
    }


}
