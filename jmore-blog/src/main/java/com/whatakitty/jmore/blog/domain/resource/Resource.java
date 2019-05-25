package com.whatakitty.jmore.blog.domain.resource;

import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import java.io.File;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * resource aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/24
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public final class Resource extends AbstractAggregateRoot<Long> {

    /**
     * allowed extensions
     */
    private static final String[] ALLOWED_EXTENSIONS = new String[] {
        "jpg", "png", "gif", "jpeg", "mp4", "avi"
    };

    /**
     * allowed max size of the uploaded file
     */
    private static final long ALLOWED_MAX_SIZE = 2 * FileUtils.ONE_MB;

    private File file;
    private User user;
    private Date uploadTime;

    /**
     * upload file
     *
     * @return the uploaded resource
     */
    public boolean upload() {
        // check status
        checkActive();

        // check file
        if (!file.exists() || !file.isFile()) {
            throw new UploadFailedException();
        }

        // check size and legal
        final String fileName = file.getName();
        if (!FilenameUtils.isExtension(fileName, ALLOWED_EXTENSIONS)) {
            throw new UploadFailedException();
        }

        return true;
    }

    /**
     * remove the resource
     *
     * @return {true} successfully while {false} failure
     */
    public boolean remove() {
        invalid();
        return true;
    }

}
