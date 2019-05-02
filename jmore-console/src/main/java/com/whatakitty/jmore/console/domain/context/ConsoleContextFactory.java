package com.whatakitty.jmore.console.domain.context;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * console context factory
 *
 * @author WhatAKitty
 * @date 2019/05/03
 * @description
 **/
@Component
public class ConsoleContextFactory {

    /**
     * build console context from application args
     *
     * @param source     the source
     * @param appContext the application context
     * @param args       args passed from command line
     * @return console context
     */
    public ConsoleContext create(final Object source,
                                 final ApplicationContext appContext,
                                 final ApplicationArguments args) {
        ConsoleContext context = new ConsoleContext(source, appContext);
        args.getOptionNames().parallelStream()
            .forEach(name -> {
                context.addParameter(name, args.getOptionValues(name));
            });
        return context;
    }

}
