package com.whatakitty.jmore.console;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;

/**
 * JMore Console Runner
 *
 * @author WhatAKitty
 * @date 2019/04/23
 * @description
 **/
public interface JMoreConsoleRunner {

    /**
     * run with console context
     *
     * @param context the console context contains args and so on.
     */
    void run(ConsoleContext context);

}
