package com.whatakitty.jmore.web.resultcode.user;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 用户请求服务错误码
 *
 * @author WhatAKitty
 * @date 2020/04/29
 * @since 1.0.0
 **/
public class UserRequestServerResultCode extends ResultCode {

    public static final ResultCode USER_REQUEST_SERVER_EXCEPTION = create("A0500", "");

    public static final ResultCode REQUEST_TIMES_EXCEED = create("A0501", "");
    public static final ResultCode REQUEST_CONCURRENCY_TIMES_EXCEED = create("A0502", "");
    public static final ResultCode USER_OPERATION_WAITING = create("A0503", "");
    public static final ResultCode WEBSOCKET_CONNECTION_EXCEPTION = create("A0504", "");
    public static final ResultCode WEBSOCKET_CONNECTION_CLOSED = create("A0505", "");
    public static final ResultCode USER_REQUEST_REPEAT = create("A0506", "");

    protected UserRequestServerResultCode(String code, String msg) {
        super(code, msg);
    }

}
