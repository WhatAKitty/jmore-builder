package com.whatakitty.jmore.web.resultcode.system;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 系统容灾功能被处罚错误码
 *
 * @author WhatAKitty
 * @date 2020/04/29
 * @since 1.0.0
 **/
public class SystemRecoveryResultCode extends ResultCode {

    public static final ResultCode SYSTEM_RECOVERY_EXCEPTION = create("B0200", "");

    public static final ResultCode SYSTEM_FLOW_LIMITED = create("B0210", "");
    public static final ResultCode SYSTEM_FUNCTION_FALLBACK = create("B0220", "");

    protected SystemRecoveryResultCode(String code, String msg) {
        super(code, msg);
    }
}
