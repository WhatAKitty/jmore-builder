package com.whatakitty.jmore.lock.zookeeper;

import com.whatakitty.jmore.lock.Lock;
import com.whatakitty.jmore.lock.exception.LockException;
import com.whatakitty.jmore.lock.exception.TimeoutLockException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.StringUtils;

/**
 * zookeeper lock
 *
 * @author WhatAKitty
 * @date 2019/02/22
 * @description
 **/
@RequiredArgsConstructor
public class ZookeeperLock implements Lock {

    private final ZookeeperLockOptions lockOptions;
    private final ZooKeeper zk;

    private String lockZnode = null;

    @Override
    public void lock() throws LockException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public void lock(long time, TimeUnit unit) throws TimeoutLockException {
        return;
    }

    @Override
    public void unLock() throws LockException {

    }

    private void _lock() throws LockException {
        ZookeeperLockUtils.ensureRootPath(zk, lockOptions.getRootPath());
        try {
            final String path = zk.create(lockOptions.getKey() + lockOptions.getRootPath(), "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            lockZnode = path;
            List<String> minPath = zk.getChildren(lockOptions.getKey(), false);
            System.out.println(minPath);
            Collections.sort(minPath);
            System.out.println(minPath.get(0) + " and path " + path);
            if (!StringUtils.isEmpty(path) && !StringUtils.isEmpty(minPath.get(0)) && path.equals(lockOptions.getRootPath() + "/" + minPath.get(0))) {
                System.out.println(Thread.currentThread().getName() + "  get Lock...");
                return;
            }
            String watchNode = null;
            for (int i = minPath.size() - 1; i >= 0; i--) {
                if (minPath.get(i).compareTo(path.substring(path.lastIndexOf("/") + 1)) < 0) {
                    watchNode = minPath.get(i);
                    break;
                }
            }

            if (watchNode != null) {
                final String watchNodeTmp = watchNode;
                final Thread thread = Thread.currentThread();
                Stat stat = zk.exists(lockOptions.getKey() + "/" + watchNodeTmp, new Watcher() {
                    @Override
                    public void process(WatchedEvent watchedEvent) {
                        if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
                            thread.interrupt();
                        }
                        try {
                            zk.exists(lockOptions.getKey() + "/" + watchNodeTmp, true);
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                });
                if (stat != null) {
                    System.out.println("Thread " + Thread.currentThread().getId() + " waiting for " + lockOptions.getKey() + "/" + watchNode);
                }
            }
            try {
                Thread.sleep(1000000000);
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName() + " notify");
                System.out.println(Thread.currentThread().getName() + "  get Lock...");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
