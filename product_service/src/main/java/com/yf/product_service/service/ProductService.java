package com.yf.product_service.service;

import com.yf.product_service.entiy.Product;

import java.util.List;

/**
 * @Package com.yf.product_service.service
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 17:30
 */
public interface ProductService {

    List<Product> listProduct();


    Product getById(String productId);
}
