package com.whatakitty.jmore.console.domain.history;

import com.whatakitty.jmore.console.domain.command.ICommand;
import org.springframework.stereotype.Component;

/**
 * command snapshot creation factory
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
@Component
public class CommandSnapshotFactory {

    /**
     * create command snapshot from command
     *
     * @param command command
     * @return command snapshot
     */
    public CommandSnapshot snapshot(ICommand command) {
        return new CommandSnapshot(command.getName(), command);
    }

}
