package com.zhangsl.dto;

import com.zhangsl.entity.OrderDetail;
import com.zhangsl.enums.OrderStatusEnum;
import com.zhangsl.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/1/29.
 */
@Data
public class OrderDto {

    //订单id
    private String orderId;

    //买家名字
    private String buyerName;

    //买家电话
    private String buyerPhone;

    //买家地址
    private String buyerAddress;

    //买家微信openid
    private String buyerOpenid;

    //订单总金额
    private BigDecimal orderAmount;

    //订单状态, 默认0为新下单
    private Integer orderStatus;

    //支付状态, 默认0未支付
    private Integer payStatus;

    private List<OrderDetail> orderDetails;
}
