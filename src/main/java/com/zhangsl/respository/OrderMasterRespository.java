package com.zhangsl.respository;

import com.zhangsl.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Zhangsl on 2018/1/29.
 */
public interface OrderMasterRespository extends JpaRepository<OrderMaster,String> {

    /**
     * 分页查询用户订单信息
     * @param buyerOpenId
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);


}
