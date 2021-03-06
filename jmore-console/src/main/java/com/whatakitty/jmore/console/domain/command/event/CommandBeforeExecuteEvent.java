package com.whatakitty.jmore.console.domain.command.event;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;

/**
 * command before execute event
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
public final class CommandBeforeExecuteEvent extends AbstractCommandDomainEvent {

    /**
     * Create a new Command Before Execute Event
     *
     * @param context the console context
     */
    public CommandBeforeExecuteEvent(final ConsoleContext context) {
        super(context);
    }

}
