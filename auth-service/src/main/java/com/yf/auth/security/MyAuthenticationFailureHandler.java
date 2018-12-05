package com.yf.auth.security;

import com.yf.utils.entiy.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package com.yf.auth.handler
 * @Description: 登录失败 处理器
 * @author: jingh
 * @date 2018/11/24 15:47
 */
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException {

        logger.info("登录失败");
        logger.info("MyAuthenticationSuccessHandler login failure!");
        if (e instanceof BadCredentialsException ||  e instanceof UsernameNotFoundException) {
            response.getWriter().write(R.error("账户名或者密码输入错误!").toJson());
        } else if (e instanceof LockedException) {
            response.getWriter().write(R.error("账户被锁定，请联系管理员!").toJson());
        } else if (e instanceof CredentialsExpiredException) {
            response.getWriter().write(R.error("密码过期，请联系管理员!").toJson());
        } else if (e instanceof AccountExpiredException) {
            response.getWriter().write(R.error("账户过期，请联系管理员!").toJson());
        } else if (e instanceof DisabledException) {
            response.getWriter().write(R.error("账户被禁用，请联系管理员!").toJson());
        } else {
            response.getWriter().write(R.error("登录失败").toJson());
        }
        logger.info( response.getWriter().toString());
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
    }
}
