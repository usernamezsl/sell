package com.zhangsl.service;

import com.zhangsl.entity.ProductCategory;

import java.util.List;

/**
 * Created by Administrator on 2018/1/28.
 */
public interface ProductCategoryService {

    /**
     * 查询一条数据
     * @param categoryId
     * @return
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * 查询所有数据
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 买家端使用
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 新增 更新
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
