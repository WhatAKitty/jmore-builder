package com.whatakitty.jmore.web.resultcode.system;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 系统执行错误码
 *
 * @author WhatAKitty
 * @date 2020/04/29
 * @since 1.0.0
 **/
public class SystemExecutionResultCode extends ResultCode {

    public static final ResultCode SYSTEM_EXECUTION_EXCEPTION = create("B0001", "");
    public static final ResultCode SYSTEM_EXECUTION_TIMEOUT = create("B0100", "");
    public static final ResultCode SYSTEM_ORDER_PROCESSING_TIMEOUT = create("B0101", "");

    protected SystemExecutionResultCode(String code, String msg) {
        super(code, msg);
    }
}
