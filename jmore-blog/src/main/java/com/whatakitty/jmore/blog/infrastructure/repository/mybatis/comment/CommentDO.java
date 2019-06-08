package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.comment;

import java.util.Date;
import lombok.Data;

@Data
public class CommentDO {
    private Long id;

    private Date gmtCreate;

    private Long gmtCreator;

    private Date gmtModified;

    private Long gmtModifier;

    private String content;

    private Long publisher;

    private Date commentTime;

    private Long target;

    private Byte targetType;

    private Boolean pendingStatus;
}