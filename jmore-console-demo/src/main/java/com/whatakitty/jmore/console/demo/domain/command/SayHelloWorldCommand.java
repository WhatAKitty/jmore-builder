package com.whatakitty.jmore.console.demo.domain.command;

import com.whatakitty.jmore.console.domain.command.Command;
import com.whatakitty.jmore.console.domain.command.IReceiver;
import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;

/**
 * Say Hello World command
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
public class SayHelloWorldCommand extends Command {

    public static final String COMMAND_TIP = "Say Hello World";

    public SayHelloWorldCommand(AggregateId<String> id) {
        super(id, COMMAND_TIP, con -> {
            System.out.println("Hello World!");
        });
    }

    @Override
    protected Object execute(ConsoleContext context, IReceiver receiver) {
        receiver.invoke(context);
        return 1;
    }

    @Override
    protected Object undo(ConsoleContext context, IReceiver receiver) {
        throw new UnsupportedOperationException("undo is not supported yet");
    }

    @Override
    public boolean supportUndo() {
        return false;
    }
}
