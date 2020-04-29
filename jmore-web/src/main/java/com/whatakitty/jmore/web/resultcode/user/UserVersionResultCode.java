package com.whatakitty.jmore.web.resultcode.user;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 用户版本错误码
 *
 * @author WhatAKitty
 * @date 2020/04/29
 * @since 1.0.0
 **/
public class UserVersionResultCode extends ResultCode {

    public static final ResultCode USER_CURRENT_VERSION_EXCEPTION = create("A0800", "");

    public static final ResultCode USER_VERSION_NOT_MATCH_WITH_SYSTEM = create("A0801", "");
    public static final ResultCode USER_VERSION_TOO_LOW = create("A0802", "");
    public static final ResultCode USER_VERSION_TOO_HIGH = create("A0803", "");
    public static final ResultCode USER_VERSION_EXPIRED = create("A0804", "");
    public static final ResultCode USER_API_REQUESTED_NOT_MATCH = create("A0805", "");
    public static final ResultCode USER_API_REQUESTED_TOO_HIGH = create("A0806", "");
    public static final ResultCode USER_API_REQUESTED_TOO_LOW = create("A0807", "");

    protected UserVersionResultCode(String code, String msg) {
        super(code, msg);
    }
}
