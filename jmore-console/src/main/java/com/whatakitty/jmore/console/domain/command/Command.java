package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.console.domain.command.event.CommandAfterExecuteEvent;
import com.whatakitty.jmore.console.domain.command.event.CommandBeforeExecuteEvent;
import com.whatakitty.jmore.console.domain.command.event.CommandExecuteFailedEvent;
import com.whatakitty.jmore.console.domain.command.event.CommandFinishedEvent;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Command aggregate root
 *
 * behavior:
 * 1. execute
 * 2. undo
 *
 * @author WhatAKitty
 * @date 2019/05/01
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Command extends AbstractAggregateRoot implements ICommand {

    /**
     * command name
     */
    private final String name;
    /**
     * the receiver to actually execute
     */
    private final IReceiver receiver;
    /**
     * the console context
     */
    @Getter(AccessLevel.PROTECTED)
    private final ConsoleContext context;

    @Override
    public void execute() {
        publishEvent(new CommandBeforeExecuteEvent(context));
        try {
            // invoke
            final Object result = execute(receiver);
            publishEvent(new CommandAfterExecuteEvent(context, result));
        } catch (Throwable e) {
            publishEvent(new CommandExecuteFailedEvent(context, e));
        }
        publishEvent(new CommandFinishedEvent(context));
    }

    @Override
    public void undo() {
        // undo
        undo(receiver);
    }

    protected abstract Object execute(IReceiver receiver);

    protected abstract Object undo(IReceiver receiver);

}
