package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.framework.ddd.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * command result
 *
 * @author WhatAKitty
 * @date 2019/05/05
 * @description
 **/
@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
public final class CommandResult extends ValueObject {

    private final boolean succ;
    private final Object result;

}
