package com.zhangsl.respository;

import com.zhangsl.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Zhangsl on 2018/1/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRespositoryTest {

    @Autowired
    ProductCategoryRespository mProductCategoryRespository;


    @Test
    public void findOneTest(){
        ProductCategory productCategory = mProductCategoryRespository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional //操作数据库完成自动回滚事物
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory("女生最爱", 7);
        ProductCategory category = mProductCategoryRespository.save(productCategory);

        //结果不为空  测试通过
        //Assert.assertNotNull(category);

        ///结果不为空  测试通过
        Assert.assertNotEquals(null,productCategory);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> integerList = Arrays.asList(2, 5);
        List<ProductCategory> result = mProductCategoryRespository.findByCategoryTypeIn(integerList);
        Assert.assertNotEquals(0,result.size());
    }

}