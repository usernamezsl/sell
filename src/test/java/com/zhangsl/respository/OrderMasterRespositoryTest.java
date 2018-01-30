package com.zhangsl.respository;

import com.zhangsl.entity.OrderMaster;
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

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/1/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRespositoryTest {

    @Autowired
    private OrderMasterRespository mOrderMasterRespository;

    private final String OPENID = "110110";

    @Test
    public void findByBuyerOpenid() throws Exception {

        PageRequest pageRequest = new PageRequest(1, 3);
        Page<OrderMaster> result = mOrderMasterRespository.findByBuyerOpenid(OPENID, pageRequest);
        Assert.assertNotEquals(0,result.getTotalElements());
    }

    @Test
    public void save(){

        OrderMaster orderMaster = new OrderMaster.Builder()
                .setOrderId("123457")
                .setBuyerName("小林")
                .setBuyerPhone("15505903237")
                .setBuyerAddress("漳州")
                .setBuyerOpenid("110110")
                .setOrderAmount(new BigDecimal(2.3))
                .setOrderStatus(OrderStatusEnum.NEW.getCode())
                .setPayStatus(PayStatusEnum.WAIT.getCode())
                .build();

        OrderMaster result = mOrderMasterRespository.save(orderMaster);
        Assert.assertNotNull(result);
    }

}