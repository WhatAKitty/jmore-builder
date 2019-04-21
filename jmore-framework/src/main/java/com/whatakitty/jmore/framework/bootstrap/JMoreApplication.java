package com.whatakitty.jmore.framework.bootstrap;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.lang.Nullable;

/**
 * JMore application bootstrap
 *
 * @author WhatAKitty
 * @date 2019/04/21
 * @description
 **/
public final class JMoreApplication extends SpringApplication {

    @Nullable
    @Getter(AccessLevel.PACKAGE)
    private ApplicationEventMulticaster applicationEventMulticaster;

    public JMoreApplication(Class<?>... primarySources) {
        super(primarySources);
    }

    public JMoreApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
        super(resourceLoader, primarySources);
    }

    @Override
    public ConfigurableApplicationContext run(String... args) {
        setMainApplicationClass(JMoreApplication.class);
        // get event bus
        this.applicationEventMulticaster = getOrCreateEventBus();

        return super.run(args);
    }

    @Override
    protected ConfigurableApplicationContext createApplicationContext() {
        ConfigurableApplicationContext applicationContext = super.createApplicationContext();

        return applicationContext;
    }

    /**
     * get or create event bus for jmore application
     * the event bus that created can be overridden by high priority event bus
     *
     * @return {null} if there is no application event multicaster could be found,
     * else return the list.
     */
    @Nullable
    private ApplicationEventMulticaster getOrCreateEventBus() {
        List<ApplicationEventMulticaster> applicationEventMulticasters = SpringFactoriesLoader
            .loadFactories(ApplicationEventMulticaster.class, this.getClassLoader());
        AnnotationAwareOrderComparator.sort(applicationEventMulticasters);

        if (applicationEventMulticasters.isEmpty()) {
            return null;
        }

        // get the highest priority application event multi casters
        return applicationEventMulticasters.get(0);
    }

    /**
     * Static helper that can be used to run a {@link JMoreApplication} from the
     * specified source using default settings.
     *
     * @param primarySource the primary source to load
     * @param args          the application arguments (usually passed from a Java main method)
     * @return the running {@link ApplicationContext}
     */
    public static ConfigurableApplicationContext run(Class<?> primarySource,
                                                     String... args) {
        return run(new Class<?>[] {primarySource}, args);
    }

    /**
     * Static helper that can be used to run a {@link JMoreApplication} from the
     * specified sources using default settings and user supplied arguments.
     *
     * @param primarySources the primary sources to load
     * @param args           the application arguments (usually passed from a Java main method)
     * @return the running {@link ApplicationContext}
     */
    public static ConfigurableApplicationContext run(Class<?>[] primarySources,
                                                     String[] args) {
        return new JMoreApplication(primarySources).run(args);
    }

    /**
     * A basic main that can be used to launch an application. This method is useful when
     * application sources are defined via a {@literal --spring.main.sources} command line
     * argument.
     * <p>
     * Most developers will want to define their own main method and call the
     * {@link #run(Class, String...) run} method instead.
     *
     * @param args command line arguments
     * @throws Exception if the application cannot be started
     * @see JMoreApplication#run(Class[], String[])
     * @see JMoreApplication#run(Class, String...)
     */
    public static void main(String[] args) throws Exception {
        JMoreApplication.run(new Class<?>[0], args);
    }

}
