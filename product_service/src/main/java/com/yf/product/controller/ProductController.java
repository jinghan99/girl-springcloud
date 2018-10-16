package com.yf.product.controller;

import com.yf.product.entiy.Product;
import com.yf.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Package com.yf.product.controller
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 17:40
 */
@RestController
@RequestMapping("api/v1/product")
@RefreshScope //动态更新配置
public class ProductController {

    @Value("${server.port}")
    private String port;

    @Value("${env.test}")
    private String env;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List listProduct(){
        return productService.listProduct();
    }

    @GetMapping("/getById")
    public Product getById(String id){
        Product product = productService.getById(id);
        Product product1 = new Product();
        BeanUtils.copyProperties(product,product1);
        product1.setName(product.getName()+" 端口来源于 - ："+port+ "   env:"+env);

        return product1;
    }
}
