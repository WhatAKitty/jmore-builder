package com.whatakitty.jmore.lock.zookeeper;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * zookeeper lock configuration
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 *
 * @see com.whatakitty.jmore.lock.EnableJMoreLock
 * @see com.whatakitty.jmore.lock.LockConfigurationSelector
 **/
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConditionalOnBean({ZooKeeper.class})
public class ZookeeperLockConfiguration {



}
