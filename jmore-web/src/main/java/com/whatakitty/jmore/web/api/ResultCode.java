package com.whatakitty.jmore.web.api;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * response code
 *
 * @author WhatAKitty
 * @date 2019/02/18
 * @description
 **/
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ResultCode implements Serializable {

    /**
     * success response
     */
    public static final ResultCode SUCCESS = create(0, "nice");
    /**
     * bad request
     */
    public static final ResultCode BAD_REQUEST = create(-400, "bad request");
    /**
     * system error
     */
    public static final ResultCode SYSTEM_ERROR = create(-1, "system error");

    /**
     * create a result code for this app
     *
     * @param code response code
     * @param msg  response msg
     * @return result code instance
     */
    protected static ResultCode create(final int code, final String msg) {
        return new ResultCode(code, msg);
    }

    private final int code;
    private final String msg;

}
