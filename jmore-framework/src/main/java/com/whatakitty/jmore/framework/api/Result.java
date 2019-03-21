package com.whatakitty.jmore.framework.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * api result to app or web
 *
 * @author WhatAKitty
 * @date 2019/02/18
 * @description
 **/
@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Result<T> implements Serializable {

    public static <T> Result<T> getSuccResult(T data) {
        return getResult(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> getResult(ResultCode resultCode) {
        return getResult(resultCode, resultCode.getMsg());
    }

    public static <T> Result<T> getResult(ResultCode resultCode, String msg) {
        return getResult(resultCode, msg, null);
    }

    public static <T> Result<T> getResult(ResultCode resultCode, T data) {
        return getResult(resultCode, resultCode.getMsg(), data);
    }

    public static <T> Result<T> getResult(ResultCode resultCode, String msg, T data) {
        String newMsg = StringUtils.isBlank(msg) ? resultCode.getMsg() : msg;
        return new Result<>(resultCode.getCode(), newMsg, data, System.currentTimeMillis());
    }

    private final int code;
    private final String msg;
    private final T data;
    private final long timestamp;

    private final List<DetailedMsg> detailedMsgs = new ArrayList<>(0);


    public Result<T> addDetailedMsg(DetailedMsg detailedMsg) {
        this.detailedMsgs.add(detailedMsg);
        return this;
    }

    public Result<T> addDetailedMsgs(Collection<DetailedMsg> detailedMsgs) {
        this.detailedMsgs.addAll(detailedMsgs);
        return this;
    }

    /**
     * detailed msg for describe more info about result.
     * such as the validation of some beans.
     */
    public static abstract class DetailedMsg implements Serializable {

        @Override
        public abstract String toString();

    }

}
