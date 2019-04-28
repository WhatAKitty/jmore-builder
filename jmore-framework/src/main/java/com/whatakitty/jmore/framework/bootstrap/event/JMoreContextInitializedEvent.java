package com.whatakitty.jmore.framework.bootstrap.event;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class JMoreContextInitializedEvent extends JMoreApplicationEvent {

    private final ConfigurableApplicationContext context;

    /**
     * Create a new {@link JMoreContextInitializedEvent} instance.
     *
     * @param application the current application
     * @param args        the arguments the application is running with
     * @param context     the context that has been initialized
     */
    public JMoreContextInitializedEvent(SpringApplication application,
                                        String[] args, ConfigurableApplicationContext context) {
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