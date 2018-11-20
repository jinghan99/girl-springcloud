package com.yf.sysuser.factory;

import com.yf.sysuser.entity.SysUserEntity;
import com.yf.utils.jwt.JWTInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Package com.yf.sysuser.factory
 * @Description: TODO
 * @author: jingh
 * @date 2018/11/19 16:44
 */
public class JwtUserFactory {
    private JwtUserFactory() {
    }

    public static JWTInfo create(SysUserEntity user) {
        return new JWTInfo(user.getUsername(),user.getUserId().toString(),user.getPassword(),mapToGrantedAuthorities(user.getRoleSignList()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
