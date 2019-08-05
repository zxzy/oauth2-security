package com.gf.oauth2.user.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "sys_user")
public class User {


    @Id
    private Integer id;

    private String username;

    private String password;

//    /**
//     * 属性里边有，表中没有的字段需要加上@Transient注解
//     */
//    @Transient
//    private List<Role> roleList;
}
