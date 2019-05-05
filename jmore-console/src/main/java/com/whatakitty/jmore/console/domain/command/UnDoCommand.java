package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.console.infrastructure.stream.StreamMgr;

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
                StreamMgr.getINSTANCE().println("Please enter the rollback index");
                int index = StreamMgr.getINSTANCE().reader().nextInt();
                context.getHistory().rollback(index);
            });
        }

    }

}
