package com.whatakitty.jmore.web.resultcode.user;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 用户请求参数结果码
 *
 * @author WhatAKitty
 * @date 2020/04/29
 * @since 1.0.0
 **/
public class UserRequestParamsResultCode extends ResultCode {

    public static final ResultCode USER_REQUEST_PARAMS_ERROR = create("A0400", "");

    public static final ResultCode CONTAINS_ILLEGAL_REDIRECT_URL = create("A0401", "");
    public static final ResultCode INVALID_USER_INPUT = create("A0402", "");
    public static final ResultCode REQUEST_REQUIRED_NULL = create("A0410", "");
    public static final ResultCode USER_ORDER_NUMBER_NULL = create("A0411", "");
    public static final ResultCode USER_ORDER_COUNT_NULL = create("A0412", "");
    public static final ResultCode MISSING_TIMESTAMP = create("A0413", "");
    public static final ResultCode ILLEGAL_TIMESTAMP = create("A0414", "");
    public static final ResultCode PARAMS_VALUE_VALID_RANGE_EXCEED = create("A0420", "");
    public static final ResultCode PARAMS_FORMAT_NOT_MATCH = create("A0421", "");
    public static final ResultCode ADDRESS_NOT_IN_SERVICE = create("A0422", "");
    public static final ResultCode TIME_NOT_IN_SERVICE = create("A0423", "");
    public static final ResultCode MONEY_EXCEED = create("A0424", "");
    public static final ResultCode COUNT_EXCEED = create("A0425", "");
    public static final ResultCode REQUEST_BATCH_COUNT_EXCEED = create("A0426", "");
    public static final ResultCode PARSE_JSON_FAILED = create("A0427", "");
    public static final ResultCode ILLEGAL_USER_INPUT = create("A0430", "");
    public static final ResultCode CONTAINS_FORBIDDEN_CHARS = create("A0431", "");
    public static final ResultCode PICTURE_CONTAINS_FORBIDDEN_INFO = create("A0432", "");
    public static final ResultCode FILE_INFRINGEMENT = create("A0433", "");
    public static final ResultCode USER_OPERATION_EXCEPTION = create("A0440", "");
    public static final ResultCode USER_PAYING_TIMEOUT = create("A0441", "");
    public static final ResultCode USER_CONFIRM_ORDER_TIMEOUT = create("A0442", "");
    public static final ResultCode ORDER_CLOSED = create("A0443", "");

    protected UserRequestParamsResultCode(String code, String msg) {
        super(code, msg);
    }
}
