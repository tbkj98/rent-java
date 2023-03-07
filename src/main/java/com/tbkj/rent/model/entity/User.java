package com.tbkj.rent.model.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "Users")
@Entity
public class User extends Base implements UserDetails {

    @Column
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @OneToMany(fetch = FetchType.LAZY)
    List<Renter> renters;

    @OneToMany(fetch = FetchType.LAZY)
    List<UserAuthority> authorities;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return super.getIsActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
       return !super.getIsActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !super.getIsActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !super.getIsActive();
    }

}
