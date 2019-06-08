package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.user;

import java.util.Date;
import lombok.Data;

@Data
public class UserDO {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte userType;

    private String username;

    private String password;

    // detailed

    private String nickName;

    private String mobile;

    private String birthday;

    // access info

    private String lastLoginIpv4;

    private Date lastLoginDate;

}