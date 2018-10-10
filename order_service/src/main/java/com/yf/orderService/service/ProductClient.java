package com.yf.orderService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Package com.yf.orderService.service
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/10 14:56
 */

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("api/v1/product/getById")
    String findById(@RequestParam(value = "id") String productId);
}
