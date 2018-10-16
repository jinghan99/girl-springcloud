package com.yf.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @Description: 接口限流
 * @author: jingh
 * @date 2018/10/11 17:36
 *
 * guaga 高并发 网关层 限流
 */
@Component
public class OrderRateLimitFilter extends ZuulFilter{


    /**
     * 建令牌桶
     * 每秒1000个令牌
     * 1000 qps
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1000);

    /**
     * 前置 过滤器
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 限流 最先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return -4;
    }

    /**
     * 判断 接口是否 拦截 限流
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        //TODO 后期 数据库配置
        if("/gateway/order/api/v1/order/save".equalsIgnoreCase(request.getRequestURI())){
            return true;
        }
        return false;
    }

    /**
     * 限流 操作
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        //如果没拿到 令牌 则 该用户 停止访问
        if(!RATE_LIMITER.tryAcquire()){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        return null;
    }
}
