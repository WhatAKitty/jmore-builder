package com.whatakitty.jmore.blog.application.resource;

import java.io.File;
import java.io.IOException;
import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * resource dto
 *
 * @author WhatAKitty
 * @date 2019/06/24
 * @description
 **/
@Data
public final class ResourceDTO {

    private File file;

    public void setFile(MultipartFile multipartFile) throws IOException {
        final String tempDir = System.getProperty("java.io.tmp");
        final File file = new File(FilenameUtils.concat(tempDir, multipartFile.getOriginalFilename()));
        multipartFile.transferTo(file);
        this.file = file;
    }

}
