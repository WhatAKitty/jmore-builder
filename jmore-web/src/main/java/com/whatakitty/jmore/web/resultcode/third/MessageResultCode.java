package com.whatakitty.jmore.web.resultcode.third;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 通知服务错误码
 *
 * @author WhatAKitty
 * @date 2020/04/30
 * @since 1.0.0
 **/
public class MessageResultCode extends ResultCode {

    public static final ResultCode MESSAGE_EXCEPTION = create("C0500", "");

    public static final ResultCode SMS_NOTIFICATION_FAILED = create("C0501", "");
    public static final ResultCode VOICE_NOTIFICATION_FAILED = create("C0502", "");
    public static final ResultCode EMAIL_NOTIFICATION_FAILED = create("C0503", "");

    protected MessageResultCode(String code, String msg) {
        super(code, msg);
    }

}
