package com.whatakitty.jmore.dfs.client.webdav;

import com.whatakitty.jmore.dfs.client.api.visitor.bean.AbstractDfsModel;
import com.whatakitty.jmore.dfs.client.api.visitor.bean.DfsItemModel;
import java.io.InputStream;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2020/07/06
 * @since 1.0.0
 **/
public class WebDavObject extends AbstractDfsModel<WebDavObjectKey> implements DfsItemModel {

    @Setter
    private InputStream inputStream;

    public WebDavObject(WebDavObjectKey objectKey) {
        super(objectKey);
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    @Override
    public String getUrl() {
        return (String) getKey();
    }

}
