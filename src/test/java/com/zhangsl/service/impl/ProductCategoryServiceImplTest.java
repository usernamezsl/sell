package com.zhangsl.service.impl;

import com.zhangsl.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Zhangsl on 2018/1/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl mProductCategoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = mProductCategoryService.findOne(1);
        //Assert.assertNotEquals(null,productCategory);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> productCategoryList = mProductCategoryService.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<Integer> integerList = Arrays.asList(2, 10);
        List<ProductCategory> categoryList = mProductCategoryService.findByCategoryTypeIn(integerList);
        Assert.assertNotEquals(0,categoryList.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("男生专享", 8);
        mProductCategoryService.save(productCategory);
        Assert.assertNotEquals(null,productCategory);
    }

}