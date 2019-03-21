package com.whatakitty.jmore.demo.infrastructure.dao.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/02/28
 * @description
 **/
@Data
public class UserDO {

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
    private Integer sex;
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
    private Boolean expired;
    /**
     * whether the account is enabled
     */
    private Boolean enabled;
    /**
     * whether the account is locked
     */
    private Boolean locked;

}
