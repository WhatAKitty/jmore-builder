package com.whatakitty.jmore.console.event;

import com.whatakitty.jmore.console.ConsoleContext;
import com.whatakitty.jmore.framework.ddd.event.DomainEvent;
import lombok.Getter;

/**
 * abstract command domain event
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
public class AbstractCommandDomainEvent extends DomainEvent {

    /**
     * the command passed by user
     */
    @Getter
    private final String command;

    /**
     * Create a new Command Domain Event.
     *
     * @param context the console context
     */
    public AbstractCommandDomainEvent(final ConsoleContext context) {
        super(context.getSource());
        this.command = context.getCommand().get();
    }


}
