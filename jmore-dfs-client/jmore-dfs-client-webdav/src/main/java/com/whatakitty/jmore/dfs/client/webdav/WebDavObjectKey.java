package com.whatakitty.jmore.dfs.client.webdav;

import com.sun.deploy.util.StringUtils;
import com.sun.tools.javac.util.List;
import com.whatakitty.jmore.dfs.client.api.domain.ObjectKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2020/07/06
 * @since 1.0.0
 **/
@Getter
@AllArgsConstructor
public class WebDavObjectKey implements ObjectKey<String> {

    private final String resourceKey;
    private final String[] categories;

    @Override
    public String getKey() {
        return String.format("/%s/%s", StringUtils.join(List.of(categories), "/"), resourceKey);
    }

}
