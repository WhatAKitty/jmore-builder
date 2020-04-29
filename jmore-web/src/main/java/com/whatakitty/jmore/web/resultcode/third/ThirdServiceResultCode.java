package com.whatakitty.jmore.web.resultcode.third;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2020/04/29
 * @since 1.0.0
 **/
public class ThirdServiceResultCode extends ResultCode {

    public static final ResultCode THIRD_SERVICE_EXCEPTION = create("C0100", "");

    public static final ResultCode THIRD_RPC_EXCEPTION = create("C0110", "");
    public static final ResultCode THIRD_RPC_NOTFOUND = create("C0111", "");
    public static final ResultCode THIRD_RPC_SERVICE_NOT_REGISTERED = create("C0112", "");
    public static final ResultCode INTERFACE_NOT_EXIST = create("C0113", "");
    public static final ResultCode MESSAGE_SERVICE_EXCEPTION = create("C0120", "");
    public static final ResultCode MESSAGE_SERVICE_PRODUCE_EXCEPTION = create("C0121", "");
    public static final ResultCode MESSAGE_SERVICE_CONSUME_EXCEPTION = create("C0122", "");
    public static final ResultCode MESSAGE_SERVICE_SUBSCRIBE_EXCEPTION = create("C0123", "");
    public static final ResultCode MESSAGE_GROUP_NOT_FOUND = create("C0124", "");
    public static final ResultCode CACHE_SERVICE_EXCEPTION = create("C0130", "");
    public static final ResultCode CACHE_KEY_TOO_LONG = create("C0131", "");
    public static final ResultCode CACHE_VALUE_TOO_LONG = create("C0132", "");
    public static final ResultCode CACHE_MEMORY_RUNOUT = create("C0133", "");
    public static final ResultCode CACHE_NOT_SUPPORTED_DATA_TYPE = create("C0134", "");
    public static final ResultCode CONFIGURATION_SERVER_EXCEPTION = create("C0140", "");
    public static final ResultCode NETWORK_RESOURCE_SERVER_EXCEPTION = create("C0150", "");
    public static final ResultCode VPN_SERVICE_EXCEPTION = create("C0151", "");
    public static final ResultCode CND_SERVICE_EXCEPTION = create("C0152", "");
    public static final ResultCode DNS_SERVICE_EXCEPTION = create("C0153", "");
    public static final ResultCode GATEWAY_SERVICE_EXCEPTION = create("C0154", "");

    protected ThirdServiceResultCode(String code, String msg) {
        super(code, msg);
    }
}
