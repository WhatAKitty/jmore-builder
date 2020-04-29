package com.whatakitty.jmore.web.resultcode.user;

import com.whatakitty.jmore.web.resultcode.ResultCode;

/**
 * 用户上传文件错误码
 *
 * @author WhatAKitty
 * @date 2020/04/29
 * @since 1.0.0
 **/
public class UserUploadFileResultCode extends ResultCode {

    public static final ResultCode USER_UPLOAD_FILE_EXCEPTION = create("A0700", "");

    public static final ResultCode USER_UPLOAD_FILE_TYPE_NOT_MATCH = create("A0701", "");
    public static final ResultCode USER_UPLOAD_FILE_TOO_BIG = create("A0702", "");
    public static final ResultCode USER_UPLOAD_PICTURE_TOO_BIG = create("A0703", "");
    public static final ResultCode USER_UPLOAD_VIDEO_TOO_BIG = create("A0704", "");
    public static final ResultCode USER_UPLOAD_ZIP_TOO_BIG = create("A0705", "");

    protected UserUploadFileResultCode(String code, String msg) {
        super(code, msg);
    }
}
