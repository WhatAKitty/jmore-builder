package com.whatakitty.jmore.console;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import org.springframework.boot.ApplicationArguments;

/**
 * console context
 *
 * @author WhatAKitty
 * @date 2019/04/23
 * @description
 **/
public class ConsoleContext {

    private static final ConsoleContextBuilder BUILDER = new ConsoleContextBuilder();

    static ConsoleContextBuilder builder() {
        return BUILDER;
    }

    /**
     * inner parameters holder
     */
    @Getter
    private final Map<String, Object> parameters = new ConcurrentHashMap<>(16);

    /**
     * user defined parameters holder
     */
    @Getter
    private final Map<String, Object> customized = new ConcurrentHashMap<>(16);

    /**
     * add init parameter
     *
     * @param key   the entry key
     * @param value the entry value
     */
    private void addParameter(String key, Object value) {
        parameters.put(key, value);
    }

    /**
     * add user defined parameter
     *
     * @param key   the entry key
     * @param value the entry value
     */
    public void define(String key, Object value) {
        customized.put(key, value);
    }

    /**
     * Console context builder
     */
    static class ConsoleContextBuilder {

        /**
         * build console context from application args
         *
         * @param args args passed from command line
         * @return console context
         */
        final ConsoleContext buildFromArgs(ApplicationArguments args) {
            ConsoleContext context = new ConsoleContext();
            args.getOptionNames().parallelStream()
                .forEach(name -> {
                    context.addParameter(name, args.getOptionValues(name));
                });
            return context;
        }

    }

}
