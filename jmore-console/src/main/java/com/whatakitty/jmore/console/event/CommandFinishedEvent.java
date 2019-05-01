package com.whatakitty.jmore.console.event;

import com.whatakitty.jmore.console.ConsoleContext;

/**
 * command finished event
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
public class CommandFinishedEvent extends AbstractCommandDomainEvent {

    /**
     * Create a new Command Domain Event.
     *
     * @param context the console context
     */
    public CommandFinishedEvent(ConsoleContext context) {
        super(context);
    }

}
