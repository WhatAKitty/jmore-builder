package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.framework.ddd.domain.BaseEntity;
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
public abstract class Receiver extends BaseEntity implements IReceiver {

    @Getter(AccessLevel.PROTECTED)
    private final IReceiver function;

}
