package com.whatakitty.jmore.blog.domain.security;

import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Data
@EqualsAndHashCode(callSuper = true)
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

    /**
     * set password with encoded base64
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = Base64.encodeBase64String(password.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * user login
     *
     * @param pendingUser the user found by username
     * @param password    the password that be input
     *                    and should be compared with the pending user's password
     * @param loginIp     login ip
     * @return {true} login successfully while {false} failure
     */
    public boolean login(User pendingUser, String password, String loginIp) {
        // no pending user could be found
        if (pendingUser == null || StringUtils.isBlank(pendingUser.getUsername())) {
            return false;
        }

        // password decode
        final String comparedPasswd = new String(Base64.decodeBase64(pendingUser.getPassword()), StandardCharsets.UTF_8);

        // password compare
        if (StringUtils.isBlank(password) || !password.equals(comparedPasswd)) {
            return false;
        }

        // set login date into user model
        pendingUser.setLastLoginDate(new Date());
        pendingUser.setLastLoginIp(loginIp);
        log.info("the user {} login successfully", pendingUser.getUsername());
        return true;
    }

    /**
     * user logout
     *
     * @param user the user to logout
     * @return {true} logout successfully while {false} failure
     */
    public boolean logout(User user) {
        if (user == null) {
            return false;
        }
        return true;
    }

    /**
     * reset user's password
     *
     * @param user the user that will be reset password
     * @return {true} reset successfully while {false} failure
     */
    public boolean resetPassword(User user) {
        if (user == null) {
            return false;
        }

        // reset
        user.setPassword(DEFAULT_PASSWORD);

        // TODO publish reset password successfully event

        return true;
    }

}
