package com.whatakitty.jmore.blog.domain.resource;

import com.whatakitty.jmore.blog.domain.security.User;
import java.io.File;
import java.util.Date;

/**
 * resource factory
 *
 * @author WhatAKitty
 * @date 2019/05/26
 * @description
 **/
public final class ResourceFactory {

    public static final ResourceFactory FACTORY = new ResourceFactory();

    /**
     * create a new resource
     *
     * @param file
     * @param publisher
     * @return
     */
    public Resource newResource(File file, User publisher) {
        // create a new resource
        Resource resource = new Resource();
        resource.setUser(publisher);
        resource.setUploadTime(new Date());
        resource.setFile(file);
        return resource;
    }

}
