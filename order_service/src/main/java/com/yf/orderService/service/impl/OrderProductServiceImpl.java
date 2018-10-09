package com.yf.orderService.service.impl;

import com.yf.orderService.entiy.OrderProduct;
import com.yf.orderService.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @Package com.yf.orderService.service.impl
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 23:18
 */

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public OrderProduct save(String OrderId, String ProductId) {

        Object obj = restTemplate.getForObject("http://product-service/api/v1/product/getById?id="+ProductId,Object.class);

        System.out.println(obj);

        OrderProduct orderProduct = new OrderProduct(OrderId,new Date(),ProductId,12);
        return orderProduct;
    }
}
