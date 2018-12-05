package com.yf.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yf.utils.entiy.R;
import com.yf.utils.jwt.JWTHelper;
import com.yf.utils.jwt.JWTInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package com.yf.auth.handler
 * @Description: 登录成功 处理
 * @author: jingh
 * @date 2018/11/24 15:50
 */
@Component("myAuthenctiationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * 日志
     */
    protected Logger       logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        logger.info("登录成功");
        JWTInfo userDetails = (JWTInfo) authentication.getPrincipal();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
//        登录成功 jwt 生成
        response.getWriter().write(R.ok(JWTHelper.generateToken(userDetails,30*60)).toJson());
    }
}

