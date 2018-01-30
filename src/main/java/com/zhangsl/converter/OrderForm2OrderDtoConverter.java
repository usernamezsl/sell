package com.zhangsl.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.bind.v2.TODO;
import com.zhangsl.Exception.SellException;
import com.zhangsl.dto.OrderDto;
import com.zhangsl.entity.OrderDetail;
import com.zhangsl.enums.ResultEnum;
import com.zhangsl.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhangsl on 2018/1/30.
 */
public class OrderForm2OrderDtoConverter {

    public static OrderDto convert(OrderForm orderForm){
        Gson gson = new Gson();

        OrderDto orderDto = new OrderDto();

        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
            // TODO: 2018/1/30 打印日志 【对象转换错误,String={}】
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetails(orderDetailList);
        return orderDto;
    }
}
