package com.whatakitty.jmore.framework.bootstrap.event;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

public class JMoreEnvironmentPreparedEvent extends JMoreApplicationEvent {

    private final ConfigurableEnvironment environment;

    /**
     * Create a new {@link JMoreEnvironmentPreparedEvent} instance.
     *
     * @param application the current application
     * @param args        the arguments the application is running with
     * @param environment the environment that was just created
     */
    public JMoreEnvironmentPreparedEvent(SpringApplication application,
                                         String[] args, ConfigurableEnvironment environment) {
        super(application, args);
        this.environment = environment;
    }

    /**
     * Return the environment.
     *
     * @return the environment
     */
    public ConfigurableEnvironment getEnvironment() {
        return this.environment;
    }

}