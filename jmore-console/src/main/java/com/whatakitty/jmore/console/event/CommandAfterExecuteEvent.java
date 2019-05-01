package com.whatakitty.jmore.console.event;

import com.whatakitty.jmore.console.ConsoleContext;

/**
 * command after execute event
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
public class CommandAfterExecuteEvent extends AbstractCommandDomainEvent {

    private final Object result;

    /**
     * Create a new Command Domain Event.
     *
     * @param context the console context
     * @param result  the result of this command
     */
    public CommandAfterExecuteEvent(ConsoleContext context, Object result) {
        super(context);
        this.result = result;
    }

}