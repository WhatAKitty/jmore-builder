package com.whatakitty.jmore.web.resultcode.system;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 系统资源异常
 *
 * @author WhatAKitty
 * @date 2020/04/29
 * @since 1.0.0
 **/
public class SystemResourceResultCode extends ResultCode {

    public static final ResultCode SYSTEM_RESOURCE_EXCEPTION = create("B0300", "");

    public static final ResultCode SYSTE_RESOURCE_RUNOUT = create("B0310", "");
    public static final ResultCode SYSTEM_DISK_RUNOUT = create("B0311", "");
    public static final ResultCode SYSTEM_MEMORY_RUNOUT = create("B0312", "");
    public static final ResultCode SYSTEM_HANDLE_RUNOUT = create("B0313", "");
    public static final ResultCode SYSTEM_CONNECTION_POOL_RUNOUT = create("B0314", "");
    public static final ResultCode SYSTEM_THREAD_POOL_RUNOUT = create("B0315", "");
    public static final ResultCode SYSTEM_RESOURCE_ACCESS_EXCEPTION = create("B0320", "");
    public static final ResultCode SYSTEM_READ_DISK_FILE_FAILED = create("B0321", "");

    protected SystemResourceResultCode(String code, String msg) {
        super(code, msg);
    }

}
