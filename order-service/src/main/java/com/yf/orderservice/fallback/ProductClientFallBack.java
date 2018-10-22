package com.yf.orderservice.fallback;

import com.yf.orderservice.service.ProductClient;
import org.springframework.stereotype.Component;

/**
 * @Description: 针对商品服务 降级处理 兜底数据 异常类
 * @author: jingh
 * @date 2018/10/10 16:54
 */
@Component
public class ProductClientFallBack implements ProductClient {
    @Override
    public String findById(String productId) {
        System.out.println("调用 product-service  findById 异常");
        return null;
    }
}
