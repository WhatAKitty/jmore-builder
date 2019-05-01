package com.whatakitty.jmore.console.demo;

import com.whatakitty.jmore.console.ConsoleContext;
import com.whatakitty.jmore.console.domain.command.Command;
import com.whatakitty.jmore.console.domain.command.ICommand;
import com.whatakitty.jmore.console.domain.command.IReceiver;

/**
 * Say Hello World command
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
public class SayHelloWorldCommand extends Command {

    public static final String COMMAND_TIP = "Say Hello World";

    public SayHelloWorldCommand(ConsoleContext context) {
        super(COMMAND_TIP, con -> {
            System.out.println("Hello World!");
        }, context);
    }

    @Override
    protected Object execute(IReceiver receiver) {
        receiver.invoke(getContext());
        return 1;
    }

    @Override
    protected Object undo(IReceiver receiver) {
        throw new UnsupportedOperationException("undo is not supported yet");
    }

    @Override
    public ICommand get() {
        return this;
    }

}
