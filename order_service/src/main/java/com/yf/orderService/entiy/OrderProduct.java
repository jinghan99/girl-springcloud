package com.yf.orderService.entiy;

import java.io.Serializable;
import java.util.Date;

/**
 * @Package com.yf.orderService.entiy
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 23:11
 */
public class OrderProduct implements Serializable {

    private String OrderNo;

    private Date createTime;

    private String productId;

    private int productNum;

    public OrderProduct(String orderNo, Date createTime, String productId, int productNum) {
        OrderNo = orderNo;
        this.createTime = createTime;
        this.productId = productId;
        this.productNum = productNum;
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

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }
}
