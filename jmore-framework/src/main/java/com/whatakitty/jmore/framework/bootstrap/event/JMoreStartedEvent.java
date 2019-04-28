package com.whatakitty.jmore.framework.bootstrap.event;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class JMoreStartedEvent extends JMoreApplicationEvent {

    private final ConfigurableApplicationContext context;

    /**
     * Create a new {@link JMoreStartedEvent} instance.
     *
     * @param application the current application
     * @param args        the arguments the application is running with
     * @param context     the context that was being created
     */
    public JMoreStartedEvent(SpringApplication application, String[] args,
                             ConfigurableApplicationContext context) {
        super(application, args);
        this.context = context;
    }

    /**
     * Return the application context.
     *
     * @return the context
     */
    public ConfigurableApplicationContext getApplicationContext() {
        return this.context;
    }

}