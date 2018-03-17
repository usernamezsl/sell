package com.zhangsl.service;

import com.zhangsl.entity.ProductCategory;

import java.util.List;

/**
 * Created by Administrator on 2018/1/28.
 */
public interface ProductCategoryService {


    ProductCategory findOne(Integer categoryId);


    List<ProductCategory> findAll();


    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);


    ProductCategory save(ProductCategory productCategory);
}
