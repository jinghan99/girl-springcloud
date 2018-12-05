package com.yf.auth.factory;

import com.yf.auth.entity.SysUserEntity;
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
        //将与用户类一对多的角色类的名称集合转换为 GrantedAuthority 集合
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
