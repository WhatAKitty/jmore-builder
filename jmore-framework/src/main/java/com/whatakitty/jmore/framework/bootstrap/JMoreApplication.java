package com.whatakitty.jmore.framework.bootstrap;

import com.whatakitty.jmore.framework.bootstrap.listener.JMoreApplicationListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
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

    @Getter
    private List<JMoreApplicationListener> jmoreListeners;

    public JMoreApplication(Class<?>... primarySources) {
        this(null, primarySources);
    }

    @SuppressWarnings("unchecked")
    public JMoreApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
        super(resourceLoader, primarySources);

        // load jmore listeners
        List<JMoreApplicationListener> applicationListeners = SpringFactoriesLoader
            .loadFactories(JMoreApplicationListener.class, this.getClassLoader());
        setJMoreListeners((Collection) applicationListeners);
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

        // if the event multi caster instance of BeanFacotryAware, then set the beanFactory in it.
        if (applicationEventMulticaster instanceof BeanFactoryAware) {
            ((BeanFactoryAware) applicationEventMulticaster).setBeanFactory(applicationContext.getBeanFactory());
        }

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
        ApplicationEventMulticaster publisher = applicationEventMulticasters.get(0);

        // set task executor
        if (publisher instanceof PooledEventPublisher) {
            ((PooledEventPublisher) publisher).setExecutor(createDefaultTaskExecutor());
        } else if (publisher instanceof SimpleApplicationEventMulticaster) {
            ((SimpleApplicationEventMulticaster) publisher).setTaskExecutor(createDefaultTaskExecutor());
        }
        return publisher;
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
     * set jmore listeners
     * will reset the jmorelisteners list
     *
     * @param listeners
     */
    private void setJMoreListeners(Collection<? extends JMoreApplicationListener<?>> listeners) {
        this.jmoreListeners = new ArrayList<>(16);
        this.jmoreListeners.addAll(listeners);
    }

    /**
     * add jmore listeners
     *
     * @param listener the listeners to add
     */
    public void addJMoreListener(JMoreApplicationListener<?>... listener) {
        this.jmoreListeners.addAll(Arrays.asList(listener));
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

    private ThreadPoolExecutor createDefaultTaskExecutor() {
        return new ThreadPoolExecutor(
            3,
            5,
            1000,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(200),
            new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

}
