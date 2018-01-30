package com.zhangsl.service;

import com.zhangsl.dto.CartDTO;
import com.zhangsl.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * Created by Zhangsl on 2018/1/28.
 */
public interface ProductInfoService {

    //查询一条数据
    ProductInfo findOne(String productInfoId);

    //查询所有已上架的商品
    List<ProductInfo> findUpAll();

    //分页查询所有
    Page<ProductInfo> findAll(Pageable pageable);

    //新增 更新
    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOS);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOS);
}
