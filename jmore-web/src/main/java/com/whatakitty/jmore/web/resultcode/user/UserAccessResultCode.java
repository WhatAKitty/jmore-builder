package com.whatakitty.jmore.web.resultcode.user;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2020/04/28
 * @since 1.0.0
 **/
public class UserAccessResultCode extends ResultCode {

    public static final ResultCode ACCESS_PERMISSION_EXCEPTION = create("A0300", "");


    public static final ResultCode NO_PERMISSION = create("A0301", "");
    public static final ResultCode PERMISSION_LANDING = create("A0302", "");
    public static final ResultCode PERMISSION_APPLY_DENIED = create("A0303", "");
    public static final ResultCode DENIED_DUE_TO_PRIVATE_ACCESS = create("A0310", "");
    public static final ResultCode AUTHORIZATION_EXPIRED = create("A0311", "");
    public static final ResultCode NO_PERMISSION_TO_USE_API = create("A0312", "");
    public static final ResultCode ACCESS_DENIED = create("A0320", "");
    public static final ResultCode BLACK_USER = create("A0321", "");
    public static final ResultCode FREEZEED = create("A0322", "");
    public static final ResultCode ILLEGAL_IP_ADDRESS = create("A0323", "");
    public static final ResultCode GATEWAY_LIMITED = create("A0324", "");
    public static final ResultCode BLACK_AREA = create("A0325", "");
    public static final ResultCode SERVICE_OVERDUE = create("A0330", "");
    public static final ResultCode USER_SIGN_EXCEPTION = create("A0340", "");
    public static final ResultCode RSA_SIGN_ERROR = create("A0341", "");


    protected UserAccessResultCode(String code, String msg) {
        super(code, msg);
    }
}
