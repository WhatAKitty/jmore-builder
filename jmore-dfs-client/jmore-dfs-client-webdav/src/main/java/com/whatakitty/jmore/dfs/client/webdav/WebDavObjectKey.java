package com.whatakitty.jmore.dfs.client.webdav;

import com.google.common.collect.Lists;
import com.whatakitty.jmore.dfs.client.api.domain.ObjectKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

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
    public String getParent() {
        return String.format("/%s", StringUtils.join(Lists.newArrayList(categories), "/"));
    }

    @Override
    public String getKey() {
        return String.format("/%s/%s", StringUtils.join(Lists.newArrayList(categories), "/"), resourceKey);
    }

}
