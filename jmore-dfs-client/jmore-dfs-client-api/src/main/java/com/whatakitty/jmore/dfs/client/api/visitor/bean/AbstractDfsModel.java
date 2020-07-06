package com.whatakitty.jmore.dfs.client.api.visitor.bean;

import com.whatakitty.jmore.dfs.client.api.domain.Object;
import com.whatakitty.jmore.dfs.client.api.domain.ObjectKey;
import com.whatakitty.jmore.dfs.client.api.visitor.Visitor;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * abstract dfs model
 *
 * @author WhatAKitty
 * @date 2020/07/07
 * @since 1.0.0
 **/
public abstract class AbstractDfsModel<T extends ObjectKey<?>> extends Object<T> implements DfsModel {

    protected AbstractDfsModel(T objectKey) {
        super(objectKey);
    }

    @Override
    public final void visit(Visitor visitor) {
        if (this instanceof DfsItemModel) {
            // visit leaf item
            visitor.visitItem((DfsItemModel) this);
            return;
        }

        // directory
        final DfsDirectoryModel dfsDirectoryModel = (DfsDirectoryModel) this;
        switch (visitor.getVisitorWay()) {
            // dfs
            case DEPTH_FIRST: {
                // visit itself
                visitor.visitDirectory(dfsDirectoryModel);

                // recursive
                final Collection<DfsModel> children = dfsDirectoryModel.getChildren();
                for (DfsModel dfsModel : children) {
                    dfsModel.visit(visitor);
                }
                break;
            }
            // bfs
            case BRANCH_FIRST: {
                Queue<DfsModel> queue = new LinkedList<>();
                queue.add(dfsDirectoryModel);

                while (!queue.isEmpty()) {
                    final int size = queue.size();

                    for (int i = 0; i < size; i++) {
                        final DfsModel current = queue.poll();
                        if (current instanceof DfsItemModel) {
                            // visit leaf item
                            visitor.visitItem((DfsItemModel) current);
                            continue;
                        }

                        // directory
                        final DfsDirectoryModel currentDirectory = (DfsDirectoryModel) current;
                        visitor.visitDirectory(currentDirectory);

                        // bfs
                        final Collection<DfsModel> children = currentDirectory.getChildren();
                        queue.addAll(children);
                    }
                }
                break;
            }
        }
    }

}
