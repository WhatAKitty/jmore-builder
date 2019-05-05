package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import org.springframework.core.Ordered;

/**
 * command interface
 *
 * @author WhatAKitty
 * @date 2019/05/01
 * @description
 **/
public interface ICommand extends Ordered {

    /**
     * get command name
     *
     * @return command name
     */
    String getName();

    /**
     * execute the command
     */
    CommandResult execute(ConsoleContext context);

    /**
     * undo the command
     */
    default void undo(ConsoleContext context) {
        if (!supportUndo()) {
            throw new UnsupportedOperationException("unsupported undo operation");
        }
    }

    /**
     * whether the command support undo operation
     *
     * @return {true} supported while {false} unsupported
     */
    boolean supportUndo();

    /**
     * the order the commands should execute as excepted
     *
     * @return the command execute order
     */
    @Override
    default int getOrder() {
        return 0;
    }

}
