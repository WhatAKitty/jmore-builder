package com.whatakitty.jmore.console;

import com.whatakitty.jmore.framework.compilerule.annotations.ThreadSafe;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.env.Environment;

/**
 * console context
 *
 * @author WhatAKitty
 * @date 2019/04/23
 * @description
 **/
@ThreadSafe
public class ConsoleContext {

    private static final ConsoleContextBuilder BUILDER = new ConsoleContextBuilder();

    static ConsoleContextBuilder builder() {
        return BUILDER;
    }

    /**
     * current command to execute
     */
    @Getter
    private final ThreadLocal<String> command = new ThreadLocal<>();

    /**
     * source
     */
    @Getter
    private final Object source;

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

    private ConsoleContext(final Object source, final ApplicationContext appContext) {
        this.source = source;
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
     * publish event
     *
     * @param event the raw event obj
     */
    public void publishEvent(ApplicationEvent event) {
        getAppContext().publishEvent(event);
    }

    /**
     * Console context builder
     */
    static class ConsoleContextBuilder {

        /**
         * build console context from application args
         *
         * @param source     the source
         * @param appContext the application context
         * @param args       args passed from command line
         * @return console context
         */
        final ConsoleContext buildFromArgs(final Object source,
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

}
