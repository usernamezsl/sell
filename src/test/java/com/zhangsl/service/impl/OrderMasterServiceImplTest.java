package com.zhangsl.service.impl;

import com.zhangsl.dto.OrderDto;
import com.zhangsl.entity.OrderDetail;
import com.zhangsl.enums.OrderStatusEnum;
import com.zhangsl.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Zhangsl on 2018/1/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterServiceImpl mOrderMasterService;

    private final String BUYEROPENID = "110110";
    private final String ORDERID = "1517231197527559212";

    @Test
    public void create() throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("廖师兄");
        orderDto.setBuyerAddress("慕课网");
        orderDto.setBuyerPhone("15505903237");
        orderDto.setBuyerOpenid(BUYEROPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1234568");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);

        orderDto.setOrderDetails(orderDetailList);
        OrderDto result = mOrderMasterService.create(orderDto);
    }

    @Test
    public void findOne() throws Exception {

        OrderDto orderDto = mOrderMasterService.findOne(ORDERID);
        Assert.assertEquals(ORDERID,orderDto.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        Page<OrderDto> orderDtoPage = mOrderMasterService.findList(BUYEROPENID, new PageRequest(0, 2));
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDto orderDto = mOrderMasterService.findOne(ORDERID);
        OrderDto result = mOrderMasterService.cancel(orderDto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDto orderDto = mOrderMasterService.findOne(ORDERID);
        OrderDto result = mOrderMasterService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDto orderDto = mOrderMasterService.findOne(ORDERID);
        OrderDto result = mOrderMasterService.paid(orderDto);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

}