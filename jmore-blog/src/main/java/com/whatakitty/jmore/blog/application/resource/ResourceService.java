package com.whatakitty.jmore.blog.application.resource;

import com.whatakitty.jmore.blog.domain.resource.Resource;
import com.whatakitty.jmore.blog.domain.resource.ResourceFactory;
import com.whatakitty.jmore.blog.domain.resource.ResourceRepository;
import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.blog.domain.security.UserRepository;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.io.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * resource service
 *
 * @author WhatAKitty
 * @date 2019/06/24
 * @description
 **/
@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    /**
     * upload file
     *
     * @param resourceDTO
     */
    public void upload(ResourceDTO resourceDTO) {
        final User currentUser = userRepository.currentUser();
        final AggregateId<Long> resourceId = resourceRepository.nextId();
        final Resource resource = ResourceFactory.FACTORY.newResource(
            resourceId,
            resourceDTO.getFile(),
            currentUser
        );
        resourceRepository.add(resource);
    }

}
