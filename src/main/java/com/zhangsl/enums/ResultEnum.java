package com.zhangsl.enums;

import lombok.Getter;

/**
 * Created by Zhangsl on 2018/1/29.
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIT(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不正常"),
    ORDER_NOT_EXIT(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIT(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}