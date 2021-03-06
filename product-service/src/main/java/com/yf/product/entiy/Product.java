package com.yf.product.entiy;

import java.io.Serializable;

/**
 * @Package com.yf.product.entity
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 17:28
 */
public class Product implements Serializable {

    private String id;

    private String name;

    private String price;

    private String store;

    private String info;


    public Product(String id, String name, String price, String store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.store = store;
    }

    public Product() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
