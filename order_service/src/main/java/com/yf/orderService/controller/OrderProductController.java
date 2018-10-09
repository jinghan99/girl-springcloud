package com.yf.orderService.controller;

import com.yf.orderService.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.yf.orderService.controller
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 23:14
 */
@RestController
@RequestMapping("api/v1/order")
public class OrderProductController {


    @Autowired
    private OrderProductService orderProductService;

    @RequestMapping("save")
    public Object save(String orderId,String productId){
        return orderProductService.save(orderId, productId);
    }
}
