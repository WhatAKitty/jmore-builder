package com.whatakitty.jmore.web.api;

import com.whatakitty.jmore.framework.validation.ValidationMsg;
import com.whatakitty.jmore.web.resultcode.ResultCode;
import java.util.stream.Collectors;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * api advice
 *
 * @author WhatAKitty
 * @date 2019/02/18
 * @description
 **/
@Component
@RestControllerAdvice(annotations = RestController.class)
public final class ApiAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    private Object handlemethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return handleBindingResultException(e.getBindingResult());
    }

    @ExceptionHandler({BindException.class})
    private Object handleBadRequestException(BindException e) {
        return handleBindingResultException(e.getBindingResult());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    private Object handleHttpMessageNotReadableException() {
        return Result.getResult(ResultCode.BAD_REQUEST, "missing body");
    }

    @ExceptionHandler({Throwable.class})
    private Object handleUncaughtException(Throwable t) {
        return Result.getResult(ResultCode.SYSTEM_ERROR, t.getMessage());
    }

    private Object handleBindingResultException(BindingResult bindingResult) {
        return Result.getResult(
            ResultCode.BAD_REQUEST,
            bindingResult.getAllErrors().stream()
                .map(ValidationMsg::new)
                .collect(Collectors.toSet())
        );
    }

}
