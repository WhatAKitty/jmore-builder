package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.console.ConsoleContext;
import com.whatakitty.jmore.console.event.CommandAfterExecuteEvent;
import com.whatakitty.jmore.console.event.CommandBeforeExecuteEvent;
import com.whatakitty.jmore.console.event.CommandExecuteFailedEvent;
import com.whatakitty.jmore.console.event.CommandFinishedEvent;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
@Component
@Scope("prototype")
@Data
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
        context.publishEvent(new CommandBeforeExecuteEvent(context));
        try {
            // invoke
            final Object result = execute(receiver);
            context.publishEvent(new CommandAfterExecuteEvent(context, result));
        } catch (Throwable e) {
            context.publishEvent(new CommandExecuteFailedEvent(context, e));
        }
        context.publishEvent(new CommandFinishedEvent(context));
    }

    @Override
    public void undo() {
        // undo
        undo(receiver);
    }

    protected abstract Object execute(IReceiver receiver);

    protected abstract Object undo(IReceiver receiver);

}
