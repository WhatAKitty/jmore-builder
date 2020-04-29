package com.whatakitty.jmore.web.resultcode.third;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 第三方容灾触发错误码
 *
 * @author WhatAKitty
 * @date 2020/04/30
 * @since 1.0.0
 **/
public class ThirdRecoveryResultCode extends ResultCode {

    public static final ResultCode THIRD_RECOVERY_TRIGGERED = create("C0400", "");

    public static final ResultCode THIRD_SERVICE_FLOW_LIMITED = create("C0401", "");
    public static final ResultCode THIRD_FUNCTION_FALLBACK = create("C0402", "");

    protected ThirdRecoveryResultCode(String code, String msg) {
        super(code, msg);
    }
}
