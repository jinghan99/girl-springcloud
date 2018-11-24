package com.yf.auth.service.impl;

import com.yf.auth.dao.SysUserMapper;
import com.yf.auth.entity.SysUserEntity;
import com.yf.auth.factory.JwtUserFactory;
import com.yf.auth.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author: jingh
 * 提供一种从用户名可以查到用户并返回的方法。注意，不一定是数据库哦，文本文件、
 * xml文件等等都可能成为数据源，
 * 这也是为什么Spring提供这样一个接口的原因：保证你可以采用灵活的数据源
 */
@Service("userDetailsService")
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    /**
     * 日志
     */
    protected Logger        logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private   SysUserMapper sysUserMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity user = sysUserMapper.getByUserName(username);
        Set<String> roleList =sysUserService.listUserRoles(user.getUserId());
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }

        String password = passwordEncoder.encode("123456");

        logger.info("password: {}", password);

        // 角色保存至 jwt token
        user.setRoleSignList(new ArrayList<>(roleList));
        return JwtUserFactory.create(user);
    }
}
