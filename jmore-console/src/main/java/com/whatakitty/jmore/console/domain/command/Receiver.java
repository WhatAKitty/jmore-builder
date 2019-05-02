package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.framework.ddd.domain.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * base receiver
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
@RequiredArgsConstructor
public abstract class Receiver extends ValueObject implements IReceiver {

    @Getter(AccessLevel.PROTECTED)
    private final IReceiver function;

}
