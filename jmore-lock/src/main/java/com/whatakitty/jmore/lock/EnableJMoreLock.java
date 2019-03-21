package com.whatakitty.jmore.lock;

import java.lang.annotation.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * jmore lock enabled annotation
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({LockConfigurationSelector.class})
@EnableConfigurationProperties(LockPropsConfiguration.class)
public @interface EnableJMoreLock {

    int order() default 2147483647;

}
