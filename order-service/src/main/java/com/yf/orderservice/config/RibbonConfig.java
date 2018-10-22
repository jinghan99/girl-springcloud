package com.yf.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Package com.yfcloud.ribbonclient.config
 * @Description: TODO
 * @author: jingh
 * @date 2018/9/6 15:30
 */
@Configuration
public class RibbonConfig {

    /**
     * 可以给RestTemplate bean 加一个@LoadBalanced注解，就能让这个RestTemplate在请求时拥有客户端负载均衡的能力：
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
