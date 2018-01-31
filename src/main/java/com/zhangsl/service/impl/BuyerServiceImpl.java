package com.zhangsl.service.impl;

import com.zhangsl.Exception.SellException;
import com.zhangsl.dto.OrderDto;
import com.zhangsl.enums.ResultEnum;
import com.zhangsl.service.BuyerService;
import com.zhangsl.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Zhangsl on 2018/1/30.
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterService mOrderMasterService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid, orderId);
        return orderDto;
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid, orderId);
        if (orderDto == null) {
            // TODO: 2018/1/30 打印日志 【取消订单】 查不到该订单
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        OrderDto result = mOrderMasterService.cancel(orderDto);
        return result;
    }

    private OrderDto checkOrderOwner(String openid, String orderId) {
        OrderDto orderDto = mOrderMasterService.findOne(orderId);
        if (orderDto == null) {
            return null;
        }
        if (!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)) {
            // TODO: 2018/1/30 打印日志 【查询订单】 订单的openid不一致,openid={} 不匹配
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
