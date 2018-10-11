package com.yf.orderservice.entiy;

import java.io.Serializable;
import java.util.Date;

/**
 * @Package com.yf.orderservice.entiy
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 23:11
 */
public class OrderProduct implements Serializable {

    private String OrderNo;

    private Date createTime;

    private String productId;

    private String productNname;

    public OrderProduct(String orderNo, Date createTime, String productId, String productNname) {
        OrderNo = orderNo;
        this.createTime = createTime;
        this.productId = productId;
        this.productNname = productNname;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductNname() {
        return productNname;
    }

    public void setProductNname(String productNname) {
        this.productNname = productNname;
    }
}
