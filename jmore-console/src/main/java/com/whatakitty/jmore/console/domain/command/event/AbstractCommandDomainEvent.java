package com.whatakitty.jmore.console.domain.command.event;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.console.domain.command.ICommand;
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
     * the console context
     */
    @Getter
    private final ConsoleContext context;

    /**
     * the command passed by user
     */
    @Getter
    private final ICommand command;

    /**
     * Create a new Command Domain Event.
     *
     * @param context the console context
     */
    public AbstractCommandDomainEvent(final ConsoleContext context) {
        super(context.getSource());
        this.context = context;
        this.command = context.getCommand().get();
    }


}
