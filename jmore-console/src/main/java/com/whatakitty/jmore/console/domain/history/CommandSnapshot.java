package com.whatakitty.jmore.console.domain.history;

import com.whatakitty.jmore.console.domain.command.ICommand;
import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.framework.ddd.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * command snapshot
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class CommandSnapshot extends AbstractEntity {

    /**
     * create command snapshot from command
     *
     * @param command command
     * @return command snapshot
     */
    public static CommandSnapshot snapshot(ICommand command) {
        return new CommandSnapshot(command.getName(), command);
    }

    private final String name;
    private final ICommand command;

    public void undo(ConsoleContext context) {
        command.undo(context);
    }

}
