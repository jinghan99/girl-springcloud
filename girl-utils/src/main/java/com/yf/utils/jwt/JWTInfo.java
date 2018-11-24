package com.yf.utils.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by ace on 2017/9/10.
 */
public class JWTInfo implements UserDetails, Serializable {
    private String username;
    private String userId;
    private String password;
    private  Collection<? extends GrantedAuthority> authorities;

    public JWTInfo(String username, String userId, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.authorities = authorities;
    }

    public JWTInfo(String username, String userId, String password) {
        this.username = username;
        this.userId = userId;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
