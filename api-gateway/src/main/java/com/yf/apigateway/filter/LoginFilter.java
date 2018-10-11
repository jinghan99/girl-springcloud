package com.yf.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 *  有4中类型：pre、post、routing和error。
filterOrder()是过滤顺序，它为一个int类型的值，值越小，越早执行该过滤器。
shouldFilter()表示是否需要执行该过滤器逻辑，true表示执行，false表示不执行，如果true则执行run()方法。
run()方法是具体的过滤的逻辑。
本例中检查请求的参数中是否传了token这个参数，如果没有传，则请求不被路由到具体的服务实例，直接返回 响应，状态码为401。
 * @author: jingh
 * @date 2018/10/11 16:26
 */


@Component
public class LoginFilter extends ZuulFilter {

    /**
     * 过滤器类型 前置
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 多个过滤器
     * 值越小，越早执行该过滤器。
     * @return
     */
    @Override
    public int filterOrder() {
        return 4;
    }

    /**
     * 是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        //getRequestURI：/gateway/order/api/v1/order/save
        //getRequestURL：http://localhost:9000/gateway/order/api/v1/order/save


//        TODO ACL 动态匹配 后期 访问控制列表

        if("/gateway/order/api/v1/order/save".equalsIgnoreCase(request.getRequestURI())){
            return true;
        }
       return false;
    }

    /**
     * 业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
//        TODO jwt拦截 后期
        //filter需要执行的具体 操作
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getParameter("token");
        System.out.println(token);

        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }
//        登录校验 逻辑
        if(StringUtils.isBlank(token)){
//            网关停止
            currentContext.setSendZuulResponse(false);
            //401 未授权
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            try {
                currentContext.getResponse().getWriter().write("token is empty UNAUTHORIZED");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        return null;
    }
}
