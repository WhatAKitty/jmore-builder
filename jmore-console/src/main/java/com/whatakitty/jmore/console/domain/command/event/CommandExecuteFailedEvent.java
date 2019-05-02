package com.whatakitty.jmore.console.domain.command.event;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;

/**
 * command execute failed event
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
public class CommandExecuteFailedEvent extends AbstractCommandDomainEvent {

    private final Throwable throwable;

    /**
     * Create a new Command Domain Event.
     *
     * @param context   the console context
     * @param throwable exception
     */
    public CommandExecuteFailedEvent(ConsoleContext context, Throwable throwable) {
        super(context);
        this.throwable = throwable;
    }

}
