package com.whatakitty.jmore.web.resultcode.user;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 用户隐私错误码
 *
 * @author WhatAKitty
 * @date 2020/04/29
 * @since 1.0.0
 **/
public class UserPrivacyResultCode extends ResultCode {

    public static final ResultCode USER_PRIVACY_NOT_ALLOWED = create("A0900", "");

    public static final ResultCode USER_PRIVACY_NOT_SIGNED = create("A0901", "");
    public static final ResultCode USER_CAMERA_HEAD_NOT_ALLOWED = create("A0902", "");
    public static final ResultCode USER_CAMERA_NOT_ALLOWED = create("A0903", "");
    public static final ResultCode USER_PHOTO_LIBRARY_NOT_ALLOWED = create("A0904", "");
    public static final ResultCode USER_FILE_NOT_ALLOWED = create("A0905", "");
    public static final ResultCode USER_LOCATION_NOT_ALLOWED = create("A0906", "");
    public static final ResultCode USER_CONTACT_NOT_ALLOWED = create("A0907", "");

    protected UserPrivacyResultCode(String code, String msg) {
        super(code, msg);
    }
}
