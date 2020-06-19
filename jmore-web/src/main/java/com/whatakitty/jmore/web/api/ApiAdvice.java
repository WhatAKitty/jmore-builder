package com.whatakitty.jmore.web.api;

import com.whatakitty.jmore.framework.validation.ValidationMsg;
import com.whatakitty.jmore.web.resultcode.system.SystemExecutionResultCode;
import com.whatakitty.jmore.web.resultcode.user.UserRequestParamsResultCode;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    private Object handlemethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return handleBindingResultException(e.getBindingResult());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BindException.class})
    private Object handleBadRequestException(BindException e) {
        return handleBindingResultException(e.getBindingResult());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    private Object handleHttpMessageNotReadableException() {
        return Result.getResult(UserRequestParamsResultCode.USER_REQUEST_PARAMS_ERROR, "missing body");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    private Object handleUncaughtException(Throwable t) {
        return Result.getResult(SystemExecutionResultCode.SYSTEM_EXECUTION_EXCEPTION, t.getMessage());
    }

    private Object handleBindingResultException(BindingResult bindingResult) {
        Result<Object> result = Result.getResult(UserRequestParamsResultCode.USER_REQUEST_PARAMS_ERROR, null);
        result.addDetailedMsgs(bindingResult.getAllErrors().stream()
            .map(ValidationMsg::new)
            .collect(Collectors.toSet()));
        return result;
    }

}
