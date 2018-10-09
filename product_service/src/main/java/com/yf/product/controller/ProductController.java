package com.yf.product.controller;

import com.yf.product.entiy.Product;
import com.yf.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package com.yf.product.controller
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 17:40
 */
@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List listProduct(){
        return productService.listProduct();
    }

    @GetMapping("/getById")
    public Product getById(String id){
        return productService.getById(id);
    }
}
