package com.yf.orderservice.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.yf.orderservice.entiy.OrderProduct;
import com.yf.orderservice.service.OrderProductService;
import com.yf.orderservice.service.ProductClient;
import com.yf.orderservice.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @Package com.yf.orderservice.service.impl
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 23:18
 */

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderProduct save(String OrderId, String ProductId) {

        logger.info("service save order");

        String json = productClient.findById(ProductId);

        JsonNode jsonNode = JsonUtils.str2JsonNode(json);

//        Map<String,Object> map = restTemplate.getForObject("http://product-service/api/v1/product/getById?id="+ProductId,Map.class);


        OrderProduct orderProduct = new OrderProduct(OrderId,new Date(),ProductId,jsonNode.get("name").toString());

        return orderProduct;
    }
}
