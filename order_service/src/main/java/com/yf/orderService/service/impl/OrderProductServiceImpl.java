package com.yf.orderService.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.yf.orderService.entiy.OrderProduct;
import com.yf.orderService.service.OrderProductService;
import com.yf.orderService.service.ProductClient;
import com.yf.orderService.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

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

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderProduct save(String OrderId, String ProductId) {

        String json = productClient.findById(ProductId);

        JsonNode jsonNode = JsonUtils.str2JsonNode(json);

//        Map<String,Object> map = restTemplate.getForObject("http://product-service/api/v1/product/getById?id="+ProductId,Map.class);


        OrderProduct orderProduct = new OrderProduct(OrderId,new Date(),ProductId,jsonNode.get("name").toString());

        return orderProduct;
    }
}
