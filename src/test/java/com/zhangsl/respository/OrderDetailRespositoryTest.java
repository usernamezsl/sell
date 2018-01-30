package com.zhangsl.respository;

import com.zhangsl.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Zhangsl on 2018/1/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRespositoryTest {

    @Autowired
    private OrderDetailRespository mOrderDetailRespository;

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12346");
        orderDetail.setOrderId("12356");
        orderDetail.setProductId("666666");
        orderDetail.setProductName("皮皮虾");
        orderDetail.setProductPrice(new BigDecimal(23));
        orderDetail.setProductQuantity(66);
        orderDetail.setProductIcon("http://xxxx.jpg");
        OrderDetail result = mOrderDetailRespository.save(orderDetail);
        Assert.assertNotNull(result);

    }
    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList = mOrderDetailRespository.findByOrderId("12356");
        Assert.assertNotEquals(0,orderDetailList.size());
    }

}