package com.yf.orderservice.service;

import com.yf.orderservice.entiy.OrderProduct;

/**
 * @Package com.yf.orderservice.service
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 23:16
 */
public interface OrderProductService {
    OrderProduct save(String OrderId,String ProductId);
}
