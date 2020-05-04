package com.whatakitty.jmore.lock.zookeeper;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * zookeeper lock configuration
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 * @see com.whatakitty.jmore.lock.EnableJMoreLock
 * @see com.whatakitty.jmore.lock.LockConfigurationSelector
 **/
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConditionalOnClass(name = {"org.apache.zookeeper.ZooKeeper"})
public class ZookeeperLockConfiguration {


}
