package com.zhangsl.converter;

import com.zhangsl.dto.OrderDto;
import com.zhangsl.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhangsl on 2018/1/30.
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDto convert(OrderMaster orderMaster){

        OrderDto orderDto = new OrderDto();

        BeanUtils.copyProperties(orderMaster,orderDto);

        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList){

        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
