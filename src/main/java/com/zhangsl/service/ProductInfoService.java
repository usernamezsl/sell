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


    ProductInfo findOne(String productInfoId);


    List<ProductInfo> findUpAll();


    Page<ProductInfo> findAll(Pageable pageable);


    ProductInfo save(ProductInfo productInfo);


    void increaseStock(List<CartDTO> cartDTOS);


    void decreaseStock(List<CartDTO> cartDTOS);
}
