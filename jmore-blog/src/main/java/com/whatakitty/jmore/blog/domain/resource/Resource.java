package com.whatakitty.jmore.blog.domain.resource;

import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.io.File;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * resource aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/24
 * @description
 **/
@Getter
@Setter
@ToString(callSuper = true)
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

    private Object target;
    private User user;
    private Date uploadTime;

    public Resource(AggregateId<Long> id) {
        super(id);
    }

    /**
     * upload file
     *
     * @return the uploaded resource
     * @throws UploadFailedException
     * @throws UnsupportedResourceTypeException
     */
    public boolean upload() {
        // check status
        checkActive();

        // check target
        if (null == target) {
            throw new UploadFailedException();
        }

        // target type
        if (target instanceof File) {
            final File file = (File) target;
            if (!file.exists() || !file.isFile()) {
                throw new UploadFailedException();
            }

            // check size and legal
            final String fileName = file.getName();
            if (!FilenameUtils.isExtension(fileName, ALLOWED_EXTENSIONS)) {
                throw new UploadFailedException();
            }
        } else {
            throw new UnsupportedResourceTypeException(target.getClass().getName());
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
