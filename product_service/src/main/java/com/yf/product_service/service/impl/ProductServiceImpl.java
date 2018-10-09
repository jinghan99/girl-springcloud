package com.yf.product_service.service.impl;

import com.yf.product_service.entiy.Product;
import com.yf.product_service.service.ProductService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @Package com.yf.product_service.service.impl
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 17:32
 */
public class ProductServiceImpl implements ProductService {


    private static final HashMap daoMap = new HashMap();


    static{

        Product p = new Product("1","桌子","2","2");
        Product p1 = new Product("2","椅子","23","4");
        Product p2 = new Product("3","杯子","32","2");
        Product p3 = new Product("4","被子","2","32");
        Product p4 = new Product("5","汤勺","1","21");
        Product p5 = new Product("6","锅","2","12");

        daoMap.put(p.getId(),p);
        daoMap.put(p1.getId(),p);
        daoMap.put(p2.getId(),p);
        daoMap.put(p3.getId(),p);
        daoMap.put(p4.getId(),p);
        daoMap.put(p5.getId(),p);
    }

    @Override
    public List<Product> listProduct() {
        Collection<Product> collection = daoMap.values();

        return new ArrayList<>(collection);
    }

    @Override
    public Product getById(String productId) {
        return (Product) daoMap.get(productId);
    }
}
