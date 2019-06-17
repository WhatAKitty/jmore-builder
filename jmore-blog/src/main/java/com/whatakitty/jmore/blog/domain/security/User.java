package com.whatakitty.jmore.blog.domain.security;

import com.whatakitty.jmore.blog.domain.security.event.UserLoginFailedEvent;
import com.whatakitty.jmore.blog.domain.security.event.UserLoginSuccessfullyEvent;
import com.whatakitty.jmore.blog.domain.security.event.UserRestPwdEvent;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * user aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/24
 * @description
 **/
@Slf4j
@Getter
@Setter
@ToString(callSuper = true)
public final class User extends AbstractAggregateRoot<Long> {

    private static final String DEFAULT_PASSWORD = "123456";

    /**
     * user type
     */
    private UserType userType;
    /**
     * user name to login
     */
    private String username;
    /**
     * the user's password that could be recognized user
     */
    private String password;
    /**
     * the user's nickname
     */
    private String nickname;
    /**
     * last login ip
     */
    private String lastLoginIp;
    /**
     * last login time
     */
    private Date lastLoginDate;

    public User(AggregateId<Long> id) {
        super(id);
    }

    /**
     * set password with encoded base64
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * user login
     *
     * @param password the password user input
     * @param loginIp  login ip
     * @return {true} login successfully while {false} failure
     */
    public boolean login(String password, String loginIp) {
        // no pending user could be found
        if (StringUtils.isBlank(this.getUsername())) {
            return false;
        }

        // compared password
        final String comparedPassword = new String(Base64.decodeBase64(password), StandardCharsets.UTF_8);

        // password compare
        if (StringUtils.isBlank(password) || !comparedPassword.equals(this.getPassword())) {
            return false;
        }

        // set login date into user model
        this.setLastLoginDate(new Date());
        this.setLastLoginIp(loginIp);
        log.info("the user {} login successfully", this.getUsername());

        // publish event
        publishEvent(new UserLoginSuccessfullyEvent(this));

        return true;
    }

    /**
     * user logout
     *
     * @return {true} logout successfully while {false} failure
     */
    public boolean logout() {
        // publish event
        publishEvent(new UserLoginFailedEvent(this));

        return true;
    }

    /**
     * reset user's password
     *
     * @return {true} reset successfully while {false} failure
     */
    public boolean resetPassword() {
        // reset
        this.setPassword(DEFAULT_PASSWORD);

        // publish event
        publishEvent(new UserRestPwdEvent(this));

        return true;
    }

}
