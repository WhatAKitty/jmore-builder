package com.whatakitty.jmore.web.resultcode.user;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2020/04/28
 * @since 1.0.0
 **/
public class UserLoginResultCode extends ResultCode {

    public static final ResultCode USER_LOGIN_EXCEPTION = create("A0200", "");

    public static final ResultCode USER_ACCOUNT_NOT_EXIST = create("A0201", "");
    public static final ResultCode USER_ACCOUNT_BE_FREEZEED = create("A0202", "");
    public static final ResultCode USER_ACCOUNT_DESTORIED = create("A0203", "");
    public static final ResultCode USER_PWD_FAILED = create("A0210", "");
    public static final ResultCode USER_INPUT_PWD_TIMES_EXCEED = create("A0211", "");
    public static final ResultCode USER_IDENTITY_FAILED = create("A0220", "");
    public static final ResultCode USER_FINGERPRINT_FAILED = create("A0221", "");
    public static final ResultCode USER_FACEID_FAILED = create("A0222", "");
    public static final ResultCode USER_NOT_GOT_THIRD_AGREEMENT = create("A0223", "");
    public static final ResultCode USER_LOGIN_EXPIRED = create("A0230", "");
    public static final ResultCode USER_CAPTCHA_FAILED = create("A02240", "");
    public static final ResultCode USER_CAPTCHA_TIMES_EXCEED = create("A0241", "");

    protected UserLoginResultCode(String code, String msg) {
        super(code, msg);
    }
}
