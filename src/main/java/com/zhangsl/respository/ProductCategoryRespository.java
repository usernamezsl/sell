package com.zhangsl.respository;

import com.zhangsl.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Zhangsl on 2018/1/28.
 */
public interface ProductCategoryRespository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
