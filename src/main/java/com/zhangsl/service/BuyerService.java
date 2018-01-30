package com.zhangsl.service;

import com.zhangsl.dto.OrderDto;

/**
 * Created by Zhangsl on 2018/1/30.
 */
public interface BuyerService {

    //查询一个订单
    public OrderDto  findOrderOne(String openid,String orderId);

    //取消订单

    public OrderDto cancelOrder(String openid,String orderId);
}
