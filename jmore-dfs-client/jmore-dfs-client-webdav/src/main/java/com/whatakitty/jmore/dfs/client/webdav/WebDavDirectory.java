package com.whatakitty.jmore.dfs.client.webdav;

import com.github.sardine.Sardine;
import com.whatakitty.jmore.dfs.client.api.visitor.bean.AbstractDfsModel;
import com.whatakitty.jmore.dfs.client.api.visitor.bean.DfsDirectoryModel;
import com.whatakitty.jmore.dfs.client.api.visitor.bean.DfsModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2020/07/07
 * @since 1.0.0
 **/
public class WebDavDirectory extends AbstractDfsModel<WebDavObjectKey> implements DfsDirectoryModel {

    private final Sardine sardine;

    protected WebDavDirectory(Sardine sardine, WebDavObjectKey objectKey) {
        super(objectKey);
        this.sardine = sardine;
    }

    @Override
    public Collection<DfsModel> getChildren() {
        try {
            return sardine.list(getUrl()).stream()
                .map(item -> VisitorHelper.createDfsModel(sardine, item))
                .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>(0);
        }
    }

    @Override
    public String getUrl() {
        return (String) getKey();
    }

}
