package com.zhangsl.respository;

import com.zhangsl.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Zhangsl on 2018/1/28.
 */
public interface ProductInfoRespository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
