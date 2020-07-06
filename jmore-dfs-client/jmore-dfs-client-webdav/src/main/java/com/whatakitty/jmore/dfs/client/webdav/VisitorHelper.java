package com.whatakitty.jmore.dfs.client.webdav;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.whatakitty.jmore.dfs.client.api.visitor.bean.DfsModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2020/07/07
 * @since 1.0.0
 **/
@Slf4j
class VisitorHelper {

    static DfsModel createDfsModel(Sardine sardine, DavResource davResource) {
        final String path = davResource.getPath();
        final String[] split = StringUtils.split(path, "/");

        final String resourceKey = split[split.length - 1];
        final String[] categories = split.length > 1 ? Arrays.copyOfRange(split, 0, split.length - 1) : new String[0];
        final WebDavObjectKey objectKey = new WebDavObjectKey(resourceKey, categories);

        if (davResource.isDirectory()) {
            return new WebDavDirectory(sardine, objectKey);
        }

        return new WebDavObject(objectKey);
    }
}
