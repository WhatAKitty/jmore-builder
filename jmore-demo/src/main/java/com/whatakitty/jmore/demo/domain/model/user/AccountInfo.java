package com.whatakitty.jmore.demo.domain.model.user;

import lombok.Value;

/**
 * account info
 *
 * @author WhatAKitty
 * @date 2019/02/27
 * @description
 **/
@Value(staticConstructor = "of")
public class AccountInfo {

    /**
     * account login name
     */
    private String username;
    /**
     * account password
     */
    private String password;
    /**
     * whether the account is expired
     */
    private boolean expired;
    /**
     * whether the account is enabled
     */
    private boolean enabled;
    /**
     * whether the account is locked
     */
    private boolean locked;

}
