package com.tbkj.rent.model.entity;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class UserAuthority extends Base implements GrantedAuthority {

    @Column
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
    
}
