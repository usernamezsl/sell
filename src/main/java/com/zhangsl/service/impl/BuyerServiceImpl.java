package com.zhangsl.service.impl;

import com.zhangsl.Exception.SellException;
import com.zhangsl.dto.OrderDto;
import com.zhangsl.enums.ResultEnum;
import com.zhangsl.service.BuyerService;
import com.zhangsl.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/1/30.
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterService mOrderMasterService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        OrderDto orderDto = mOrderMasterService.findOne(orderId);
        if (!orderDto.getBuyerOpenid().equals(openid)) {
            // TODO: 2018/1/30 打印日志 判断 openid={} 不匹配
            throw new SellException(ResultEnum.OPEN_ID_ERROR);
        }
        return orderDto;
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
        OrderDto orderDto = findOrderOne(openid, orderId);
        OrderDto result = mOrderMasterService.cancel(orderDto);
        return result;
    }
}
