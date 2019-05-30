package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;

/**
 * show history command
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
public class ShowHistoryCommand extends Command {

    public static final String COMMAND_TIP = "history";

    public ShowHistoryCommand(AggregateId<String> id) {
        super(id, COMMAND_TIP, new ShowHistoryReceiver());
    }

    @Override
    protected Object execute(ConsoleContext context, IReceiver receiver) {
        receiver.invoke(context);
        return null;
    }

    @Override
    public boolean supportUndo() {
        return false;
    }

    /**
     * show history receiver
     */
    public static final class ShowHistoryReceiver extends Receiver {

        public ShowHistoryReceiver() {
            super(context -> context.getHistory().showHistory());
        }

    }

}
