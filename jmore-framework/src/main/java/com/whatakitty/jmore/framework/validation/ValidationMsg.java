package com.whatakitty.jmore.framework.validation;

import com.whatakitty.jmore.framework.api.Result.DetailedMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * validation detail msg
 *
 * @author WhatAKitty
 * @date 2019/02/19
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationMsg extends DetailedMsg {

    private final String key;
    private final String msg;

    public ValidationMsg(ObjectError objectError) {
        this.key = objectError instanceof FieldError ? ((FieldError) objectError).getField() : objectError.getObjectName();
        this.msg = objectError.getDefaultMessage();
    }

}
