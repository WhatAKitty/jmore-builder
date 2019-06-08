package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.type;

import java.util.Date;
import lombok.Data;

@Data
public class TypeDO {
    private Long id;

    private Date gmtCreate;

    private Long gmtCreator;

    private Date gmtModified;

    private Long gmtModifier;

    private String code;

    private String typeName;
}