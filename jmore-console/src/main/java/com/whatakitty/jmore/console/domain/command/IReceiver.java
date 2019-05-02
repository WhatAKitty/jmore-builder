package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.console.ConsoleContext;

/**
 * receiver
 *
 * @author WhatAKitty
 * @date 2019/05/01
 * @description
 **/
@FunctionalInterface
public interface IReceiver {

    /**
     * receiver invoke the action while receive command
     *
     * @param context console context
     */
    void invoke(ConsoleContext context);

}
