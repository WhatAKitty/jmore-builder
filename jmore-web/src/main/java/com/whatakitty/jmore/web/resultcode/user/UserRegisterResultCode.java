package com.whatakitty.jmore.web.resultcode.user;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * The Result Code of user side.
 *
 * @author WhatAKitty
 * @date 2020/04/28
 * @since 1.0.0
 **/
public class UserRegisterResultCode extends ResultCode {

    public static final ResultCode USER_DISAGREE_PRIVACY_AGREEMENT = create("A0101", "");
    public static final ResultCode USER_REGISTER_LIMITED_COUNTRY = create("A0102", "");
    public static final ResultCode USER_NAME_INVALID = create("A0110", "");
    public static final ResultCode USER_NAME_EXISTED = create("A0111", "");
    public static final ResultCode USER_NAME_CONTAINS_SENCITIVE_WORDS = create("A0112", "");
    public static final ResultCode USER_NAME_CONTAINS_SPECIAL_CHARS = create("A0113", "");
    public static final ResultCode USER_PWD_FAILED = create("A0120", "");
    public static final ResultCode USER_PWD_LENGTH_TOO_SHORT = create("A0121", "");
    public static final ResultCode USER_PWD_STRONG_NOT_ENOUGH = create("A0122", "");
    public static final ResultCode USER_CAPTCHA_FAILD = create("A0130", "");
    public static final ResultCode USER_SMS_CAPTCHA_FAILD = create("A0131", "");
    public static final ResultCode USER_EMAIL_CAPTCHA_FAILD = create("A0132", "");
    public static final ResultCode USER_VOICE_CAPTCHA_FAILD = create("A0133", "");
    public static final ResultCode USER_CERT_STATE_EXCEPTION = create("A0140", "");
    public static final ResultCode USER_CERT_TYPE_NOT_CHOOSEN = create("A0141", "");
    public static final ResultCode USER_MAINLAND_IDCARD_INVALID = create("A0142", "");
    public static final ResultCode USER_PASSPORT_INVALID = create("A0143", "");
    public static final ResultCode USER_OFFICER_ID_INVALID = create("A0144", "");
    public static final ResultCode USER_BASIC_INFO_INVALID = create("A0150", "");
    public static final ResultCode USER_MOBILE_INVALID = create("A0151", "");
    public static final ResultCode USER_ADDRESS_INVALID = create("A0152", "");
    public static final ResultCode USER_EMAIL_INVALID = create("A0153", "");

    protected UserRegisterResultCode(String code, String msg) {
        super(code, msg);
    }

}
