package com.whatakitty.jmore.framework.bootstrap.event;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class JMoreFailedEvent extends JMoreApplicationEvent {

    private final ConfigurableApplicationContext context;

    private final Throwable exception;

    /**
     * Create a new {@link JMoreFailedEvent} instance.
     *
     * @param application the current application
     * @param args        the arguments the application was running with
     * @param context     the context that was being created (maybe null)
     * @param exception   the exception that caused the error
     */
    public JMoreFailedEvent(SpringApplication application, String[] args,
                            ConfigurableApplicationContext context, Throwable exception) {
        super(application, args);
        this.context = context;
        this.exception = exception;
    }

    /**
     * Return the application context.
     *
     * @return the context
     */
    public ConfigurableApplicationContext getApplicationContext() {
        return this.context;
    }

    /**
     * Return the exception that caused the failure.
     *
     * @return the exception
     */
    public Throwable getException() {
        return this.exception;
    }

}