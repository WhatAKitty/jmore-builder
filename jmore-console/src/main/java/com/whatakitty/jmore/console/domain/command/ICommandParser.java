package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;

/**
 * command parser
 *
 * @author WhatAKitty
 * @date 2019/05/01
 * @description
 **/
public interface ICommandParser {

    /**
     * parse command string to command
     *
     * @param context the console context
     * @param command the command to execute
     * @return parsed command instance
     */
    ICommand parse(ConsoleContext context, String command);

}
