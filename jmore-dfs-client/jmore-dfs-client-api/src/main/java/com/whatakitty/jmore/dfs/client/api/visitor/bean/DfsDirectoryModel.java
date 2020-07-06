package com.whatakitty.jmore.dfs.client.api.visitor.bean;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2020/07/07
 * @since 1.0.0
 **/
public interface DfsDirectoryModel extends DfsModel {

    Collection<DfsModel> getChildren();

}
