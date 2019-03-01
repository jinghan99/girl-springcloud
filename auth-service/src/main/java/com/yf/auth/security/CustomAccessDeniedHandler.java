package com.yf.auth.security;

import com.yf.utils.common.WebUtils;
import com.yf.utils.entiy.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package com.yf.auth.security
 * @Description:  自定义权限不足处理
 * @author: jingh
 * @date 2018/12/5 15:04
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        if (WebUtils.isAjax(request)) {// AJAX请求,使用response发送403
            response.sendError(403);
            response.getWriter().write(R.error(403,"无权限访问!").toJson());
        } else if (!response.isCommitted()) {// 非AJAX请求，跳转系统默认的403错误界面，在web.xml中配置

            response.sendRedirect("/view/error/403.jsp");
            response.getWriter().write(R.error(403,"无权限访问! 跳转 无权限访问界面").toJson());
        }
    }
}
