package com.yf.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @EnableZuulProxy zuul 网关开启
 * @auther: jinghan
 * @date: 2018/10/11 15:15
 *
 * TODO  nginx + lvs  负载均衡  2个 网关
 *
 * http://localhost:9000/gateway/order/api/v1/order/save?orderId=1&productId=3&token=12313
 */
@SpringBootApplication
@EnableZuulProxy
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
