package com.whatakitty.jmore.web.resultcode;

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
    public static final ResultCode SUCCESS = create("00000", "nice");

    /**
     * create a result code for this app
     *
     * @param code response code
     * @param msg  response msg
     * @return result code instance
     */
    protected static ResultCode create(final String code, final String msg) {
        return new ResultCode(code, msg);
    }

    private final String code;
    private final String msg;

}
