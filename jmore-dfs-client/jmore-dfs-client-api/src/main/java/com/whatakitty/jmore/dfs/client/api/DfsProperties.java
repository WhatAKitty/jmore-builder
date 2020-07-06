package com.whatakitty.jmore.dfs.client.api;

import lombok.Data;

/**
 * dfs configuration
 *
 * @author WhatAKitty
 * @date 2020/07/05
 * @since 1.0.0
 **/
@Data
public abstract class DfsProperties {

    private String type;
    private String endpoint;
    private String accessKey;
    private String accessSecret;

}
