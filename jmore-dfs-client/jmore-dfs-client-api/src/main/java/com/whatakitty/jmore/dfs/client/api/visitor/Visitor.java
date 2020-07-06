package com.whatakitty.jmore.dfs.client.api.visitor;

import com.whatakitty.jmore.dfs.client.api.visitor.bean.DfsDirectoryModel;
import com.whatakitty.jmore.dfs.client.api.visitor.bean.DfsItemModel;

/**
 * to visit the objects in dfs
 *
 * @author WhatAKitty
 * @date 2020/07/05
 * @since 1.0.0
 **/
public interface Visitor {

    /**
     * the way to visit objects
     *
     * @return visitor way {@link VisitorWayEnum}
     */
    VisitorWayEnum getVisitorWay();

    /**
     * visit directory
     *
     * @param directory the directory model
     */
    void visitDirectory(DfsDirectoryModel directory);

    /**
     * visit object
     *
     * @param object the object model
     */
    void visitItem(DfsItemModel object);

}
