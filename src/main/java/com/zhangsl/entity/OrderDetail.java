package com.zhangsl.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by Zhangsl on 2018/1/29.
 */
@Data
@Entity
public class OrderDetail {

    @Id
    private String detailId;

    //订单id
    private String orderId;

    //商品id
    private String productId;

    //商品名称
    private String productName;

    //当前价格,单位分
    private BigDecimal productPrice;

    //数量
    private Integer productQuantity;

    //小图
    private String productIcon;

    public OrderDetail() {
    }
}
