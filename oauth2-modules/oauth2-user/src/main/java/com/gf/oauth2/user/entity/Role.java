package com.gf.oauth2.user.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="sys_role")
public class Role {

    @Id
    private Integer id;

    private String roleName;
}
