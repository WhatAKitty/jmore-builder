package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;

/**
 * undo command
 *
 * @author WhatAKitty
 * @date 2019/05/03
 * @description
 **/
public class UnDoCommand extends Command {

    public static final String COMMAND_TIP = "undo";

    public UnDoCommand() {
        super(COMMAND_TIP, new UnDoReceiver());
    }

    @Override
    protected Object execute(ConsoleContext context, IReceiver receiver) {
        receiver.invoke(context);
        return 1;
    }

    @Override
    public boolean supportUndo() {
        return false;
    }

    public static class UnDoReceiver extends Receiver {
        public UnDoReceiver() {
            super(context -> {
                context.getHistory().showHistory();
                context.getWriter().println("Please enter the rollback index");
                int index = context.getReader().nextInt();
                context.getHistory().rollback(index);
            });
        }

        @Override
        public void invoke(ConsoleContext context) {
            getFunction().invoke(context);
        }

    }

}
