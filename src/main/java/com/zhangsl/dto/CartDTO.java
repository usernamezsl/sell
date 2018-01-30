package com.zhangsl.dto;

import lombok.Data;

/**
 * 购物车
 * Created by Zhangsl on 2018/1/29.
 */
@Data
public class CartDTO {

    //商品 id
    private String productId;

    //数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
