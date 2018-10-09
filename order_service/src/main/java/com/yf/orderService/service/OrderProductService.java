package com.yf.orderService.service;

import com.yf.orderService.entiy.OrderProduct;

/**
 * @Package com.yf.orderService.service
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 23:16
 */
public interface OrderProductService {
    OrderProduct save(String OrderId,String ProductId);
}
