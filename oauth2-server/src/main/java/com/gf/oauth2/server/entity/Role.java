package com.gf.oauth2.server.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Role implements GrantedAuthority {

    private Integer id;

    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
