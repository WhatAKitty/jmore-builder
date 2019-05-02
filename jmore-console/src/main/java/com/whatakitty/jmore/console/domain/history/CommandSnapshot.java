package com.whatakitty.jmore.console.domain.history;

import com.whatakitty.jmore.console.domain.command.ICommand;
import com.whatakitty.jmore.framework.ddd.domain.AbstractEntity;
import lombok.Value;

/**
 * command snapshot
 *
 * @author WhatAKitty
 * @date 2019/05/02
 * @description
 **/
@Value
public class CommandSnapshot extends AbstractEntity {

    private final String name;
    private final ICommand command;

    public void undo() {
        command.undo();
    }

}
