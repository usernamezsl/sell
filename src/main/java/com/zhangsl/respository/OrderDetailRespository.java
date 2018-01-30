package com.zhangsl.respository;

import com.zhangsl.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Zhangsl on 2018/1/29.
 */
public interface OrderDetailRespository extends JpaRepository<OrderDetail,String> {


    List<OrderDetail> findByOrderId(String orderId);
}
