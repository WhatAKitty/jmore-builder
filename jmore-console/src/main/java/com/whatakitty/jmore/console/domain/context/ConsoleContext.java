package com.whatakitty.jmore.console.domain.context;

import com.whatakitty.jmore.console.domain.command.ICommand;
import com.whatakitty.jmore.console.domain.history.History;
import com.whatakitty.jmore.console.infrastructure.stream.PrintStreamAdapter;
import com.whatakitty.jmore.framework.compilerule.annotations.ThreadSafe;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * console context
 *
 * @author WhatAKitty
 * @date 2019/04/23
 * @description
 **/
@ThreadSafe
public class ConsoleContext extends AbstractAggregateRoot<String> {

    private static final Object HISTORY_LOCK = new Object();

    /**
     * the current context's commands history
     */
    @Getter
    private History history;

    /**
     * current command to execute
     */
    @Getter
    private final ThreadLocal<ICommand> command = new ThreadLocal<>();

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

    /**
     * the writer to print message
     */
    @Getter
    private final PrintStreamAdapter writer = new PrintStreamAdapter(System.out);
    /**
     * the reader to read message
     */
    @Getter
    private final Scanner reader = new Scanner(System.in);

    ConsoleContext(final Object source, final ApplicationContext appContext) {
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
    void addParameter(String key, Object value) {
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
     * bind history into this console context
     *
     * @param fetcher not exists history, do get it from fetcher
     */
    public History bindHistory(HistoryFetcher fetcher) {
        if (this.history != null) {
            return this.history;
        }

        synchronized (HISTORY_LOCK) {
            if (this.history == null) {
                this.history = fetcher.invoke();
            }
            return this.history;
        }
    }

    public void setCurrentCommand(ICommand cmd) {
        command.set(cmd);
    }

    public ICommand currentCommand() {
        return command.get();
    }

    public void removeCommand() {
        command.remove();
    }

    @FunctionalInterface
    public interface HistoryFetcher {

        History invoke();

    }

}
