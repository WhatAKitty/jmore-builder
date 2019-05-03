package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.console.domain.command.Command;
import com.whatakitty.jmore.console.domain.command.ICommand;
import com.whatakitty.jmore.console.domain.command.IReceiver;
import com.whatakitty.jmore.console.domain.command.Receiver;

/**
 * show history command
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
public class ShowHistoryCommand extends Command {

    public static final String COMMAND_TIP = "history";

    public ShowHistoryCommand() {
        super(COMMAND_TIP, new ShowHistoryReceiver());
    }

    @Override
    protected Object execute(ConsoleContext context, IReceiver receiver) {
        receiver.invoke(context);
        return null;
    }

    @Override
    protected Object undo(ConsoleContext context, IReceiver receiver) {
        return null;
    }

    @Override
    public ICommand get() {
        return this;
    }

    /**
     * show history receiver
     */
    public static final class ShowHistoryReceiver extends Receiver {

        public ShowHistoryReceiver() {
            super(context -> context.getHistory().showHistory());
        }

        @Override
        public void invoke(ConsoleContext context) {
            getFunction().invoke(context);
        }

    }

}
