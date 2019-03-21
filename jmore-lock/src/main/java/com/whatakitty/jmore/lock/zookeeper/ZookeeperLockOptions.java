package com.whatakitty.jmore.lock.zookeeper;

import com.whatakitty.jmore.lock.LockOptions;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ZookeeperLockOptions extends LockOptions {

    private final String rootPath;

    public ZookeeperLockOptions(String key, String rootPath) {
        super(key);
        this.rootPath = rootPath;
    }

}
