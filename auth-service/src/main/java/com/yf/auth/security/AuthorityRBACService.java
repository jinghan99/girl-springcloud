package com.yf.auth.security;

import com.yf.auth.entity.SysRoleEntity;
import com.yf.auth.service.SysRoleService;
import com.yf.utils.jwt.JWTInfo;
import org.apache.shiro.util.AntPathMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Package com.yf.auth.handler
 * @Description: 检测是否具有权限
 * @author: jingh
 * @date 2018/12/5 11:47
 */
@Component("authorityRBACService")
public class AuthorityRBACService {

    @Autowired
    private SysRoleService sysRoleService;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        //相信jwt  token别人无法改签
        Object userInfo = authentication.getPrincipal();

        boolean hasPermission  = false;

        if (userInfo instanceof JWTInfo) {
            Collection<? extends GrantedAuthority> authorities = ((JWTInfo) userInfo).getAuthorities();
            //获取资源
            Set<String> urls = new HashSet();
            if(authorities !=null ){
                //依据角色信息 获取可访问的 url
                for(GrantedAuthority  authority: authorities ){
                    if(null !=authority.getAuthority() ){
                        SysRoleEntity roleBySign = sysRoleService.getRoleBySign(authority.getAuthority());
                        urls.addAll(roleBySign.getMenuUrlList());
                    }
                }
            }
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            for (String url : urls) {
                if (null != url && antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
            return hasPermission;
        } else {
            return false;
        }
    }
}
