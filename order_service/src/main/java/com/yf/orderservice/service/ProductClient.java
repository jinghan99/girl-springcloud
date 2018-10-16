package com.yf.orderservice.service;

import com.yf.orderservice.fallback.ProductClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/10 14:56
 */

@FeignClient(value = "product-service",fallback = ProductClientFallBack.class)
public interface ProductClient {

    @GetMapping("api/v1/product/getById")
    String findById(@RequestParam(value = "id") String productId);
}
