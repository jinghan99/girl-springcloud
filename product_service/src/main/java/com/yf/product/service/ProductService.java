package com.yf.product.service;

import com.yf.product.entiy.Product;

import java.util.List;

/**
 * @Package com.yf.product.service
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 17:30
 */
public interface ProductService {

    List<Product> listProduct();


    Product getById(String productId);
}
