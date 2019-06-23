package com.whatakitty.jmore.blog.application.resource;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * resource api
 *
 * @author WhatAKitty
 * @date 2019/06/24
 * @description
 **/
@RestController
@RequestMapping(path = "/resources")
@RequiredArgsConstructor
public class ResourceApi {

    private final ResourceService resourceService;

    /**
     * upload file
     *
     * @param multipartFile
     * @throws IOException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(MultipartFile multipartFile) throws IOException {
        final ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setFile(multipartFile);
        resourceService.upload(resourceDTO);
    }

}
