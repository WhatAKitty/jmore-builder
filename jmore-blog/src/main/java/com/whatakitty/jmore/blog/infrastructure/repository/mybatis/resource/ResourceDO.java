package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.resource;

import java.util.Date;
import lombok.Data;

@Data
public class ResourceDO {
    private Long id;

    private Date gmtCreate;

    private Long gmtCreator;

    private Date gmtModified;

    private Long gmtModifier;

    private String name;

    private Long uploader;

    private Date uploadTime;

    private String urlRef;
}