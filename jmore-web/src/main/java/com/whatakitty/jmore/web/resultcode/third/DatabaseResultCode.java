package com.whatakitty.jmore.web.resultcode.third;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 数据库错误码
 *
 * @author WhatAKitty
 * @date 2020/04/30
 * @since 1.0.0
 **/
public class DatabaseResultCode extends ResultCode {

    public static final ResultCode DATABASE_SERVICE_EXCEPTION = create("C0300", "");

    public static final ResultCode TABLE_NOT_EXIST = create("C0311", "");
    public static final ResultCode COLUMN_NOT_EXIST = create("C0312", "");
    public static final ResultCode AMBIGUOUS_COLUMN_BETWEEN_DATABASE = create("C0321", "");
    public static final ResultCode DEAD_LOCK = create("C0331", "");
    public static final ResultCode DUPLICATE_KEY = create("C0341", "");

    protected DatabaseResultCode(String code, String msg) {
        super(code, msg);
    }
}
