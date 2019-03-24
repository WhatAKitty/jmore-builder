package com.whatakitty.jmore.lock.zookeeper;

import com.whatakitty.jmore.lock.exception.LockException;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * zookeeper lock utils
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
@Slf4j
final class ZookeeperLockUtils {

    static void ensureRootPath(ZooKeeper zk, String path) throws LockException {
        try {
            if (zk.exists(path, true) == null) {
                zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            throw new LockException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new LockException("interrupted");
        }
    }

}
