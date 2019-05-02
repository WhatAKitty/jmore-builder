package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.framework.ddd.domain.AbstractEntity;

/**
 * command parser
 *
 * @author WhatAKitty
 * @date 2019/05/01
 * @description
 **/
public abstract class CommandParser extends AbstractEntity {

    /**
     * parse command string to command
     *
     * @param context the console context
     * @param command the command to execute
     * @return parsed command instance
     */
    public abstract ICommand parse(ConsoleContext context, String command);

}
