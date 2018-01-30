package com.zhangsl.service;

import com.zhangsl.dto.OrderDto;
import com.zhangsl.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Zhangsl on 2018/1/29.
 */
public interface OrderMasterService {

    //创建订单
    OrderDto create(OrderDto orderDto);

    //查询单个订单
    OrderDto findOne(String orderId);

    //查询订单列表
    Page<OrderDto> findList(String buyerOpenId, Pageable pageable);

    //取消订单
    OrderDto cancel(OrderDto orderDto);

    //完结订单
    OrderDto finish(OrderDto orderDto);

    //支付订单
    OrderDto paid(OrderDto orderDto);
}
