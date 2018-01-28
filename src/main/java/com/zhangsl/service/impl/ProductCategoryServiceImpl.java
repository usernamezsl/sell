package com.zhangsl.service.impl;

import com.zhangsl.entity.ProductCategory;
import com.zhangsl.respository.ProductCategoryRespository;
import com.zhangsl.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zhangsl on 2018/1/28.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRespository mProductCategoryRespository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        ProductCategory productCategory = mProductCategoryRespository.findOne(categoryId);
        return productCategory;
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> productCategoryList = mProductCategoryRespository.findAll();
        return productCategoryList;
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<ProductCategory> productCategoryList = mProductCategoryRespository.findByCategoryTypeIn(categoryTypeList);
        return productCategoryList;
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        ProductCategory category = mProductCategoryRespository.save(productCategory);
        return category;
    }
}
