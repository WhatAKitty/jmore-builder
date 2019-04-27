package com.whatakitty.jmore.console;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

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
    @Getter(AccessLevel.PROTECTED)
    private final Map<String, Object> parameters = new ConcurrentHashMap<>(16);

    /**
     * user defined parameters holder
     */
    @Getter(AccessLevel.PROTECTED)
    private final Map<String, Object> customized = new ConcurrentHashMap<>(16);

    /**
     * application raw context
     */
    @Getter(AccessLevel.PROTECTED)
    private final ApplicationContext appContext;
    /**
     * environment of this application
     */
    @Getter(AccessLevel.PROTECTED)
    private final Environment environment;

    private ConsoleContext(ApplicationContext appContext) {
        this.appContext = appContext;
        this.environment = appContext.getEnvironment();
    }

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
     * get the key from this context
     *
     * priority: user defined -> init parameters -> application environment properties
     *
     * @param key          the key
     * @param defaultValue no value and then return it
     * @param <T>          parameter type
     * @return the actual value or the default value returned
     */
    @SuppressWarnings("unchecked")
    public <T> T getValue(String key, T defaultValue) {
        if (getCustomized().containsKey(key)) {
            return (T) getCustomized().get(key);
        }
        if (getParameters().containsKey(key)) {
            return (T) getParameters().get(key);
        }
        if (getEnvironment().containsProperty(key)) {
            return (T) getEnvironment().getProperty(key);
        }

        return defaultValue;
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
        final ConsoleContext buildFromArgs(ApplicationContext appContext, ApplicationArguments args) {
            ConsoleContext context = new ConsoleContext(appContext);
            args.getOptionNames().parallelStream()
                .forEach(name -> {
                    context.addParameter(name, args.getOptionValues(name));
                });
            return context;
        }

    }

}
