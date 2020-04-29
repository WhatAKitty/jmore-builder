package com.whatakitty.jmore.web.resultcode.user;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 用户资源错误码
 *
 * @author WhatAKitty
 * @date 2020/04/29
 * @since 1.0.0
 **/
public class UserResourceResultCode extends ResultCode {

    public static final ResultCode USER_RESOURCE_EXCEPTION = create("A0600", "");

    public static final ResultCode USER_BALANCE_NOT_ENOUGH = create("A0601", "");
    public static final ResultCode USER_DISK_NOT_ENOUGH = create("A0602", "");
    public static final ResultCode USER_MEMORY_NOT_ENOUGH = create("A0603", "");
    public static final ResultCode USER_OSS_MEMORY_NOT_ENOUGH = create("A0604", "");
    public static final ResultCode USER_QUOTA_NOT_ENOUGH = create("A0605", "");


    protected UserResourceResultCode(String code, String msg) {
        super(code, msg);
    }
}
