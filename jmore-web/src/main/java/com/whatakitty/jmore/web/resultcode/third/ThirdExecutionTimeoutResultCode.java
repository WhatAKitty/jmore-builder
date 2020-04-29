package com.whatakitty.jmore.web.resultcode.third;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 第三方服务执行超时
 *
 * @author WhatAKitty
 * @date 2020/04/30
 * @since 1.0.0
 **/
public class ThirdExecutionTimeoutResultCode extends ResultCode {

    public static final ResultCode THIRD_EXECUTION_TIMEOUT = create("C0200", "");

    public static final ResultCode RPC_EXECUTION_TIMEOUT = create("C0210", "");
    public static final ResultCode MESSAGE_PRODUCE_TIEMOUT = create("C0220", "");
    public static final ResultCode CACHE_SERVICE_TIMEOUT = create("C0230", "");
    public static final ResultCode CONFIGURATION_SERVICE_TIMEOUT = create("C0240", "");
    public static final ResultCode DATABASE_SERVICE_TIMEOUT = create("C0250", "");

    protected ThirdExecutionTimeoutResultCode(String code, String msg) {
        super(code, msg);
    }
}
