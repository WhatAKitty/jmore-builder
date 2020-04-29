package com.whatakitty.jmore.web.resultcode.user;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 用户设备错误码
 *
 * @author WhatAKitty
 * @date 2020/04/29
 * @since 1.0.0
 **/
public class UserEquipmentResultCode extends ResultCode {

    public static final ResultCode USER_EQUIPMENT_EXCEPTION = create("A1000", "");

    public static final ResultCode USER_CAMERA_EXCEPTION = create("A1001", "");
    public static final ResultCode USER_MICROPHONE_EXCEPTION = create("A1002", "");
    public static final ResultCode USER_STETHOSCOPE_EXCEPTION = create("A1003", "");
    public static final ResultCode USER_TRUMP_EXCEPTION = create("A1004", "");
    public static final ResultCode USER_GPS_EXCEPTION = create("A1005", "");

    protected UserEquipmentResultCode(String code, String msg) {
        super(code, msg);
    }
}
