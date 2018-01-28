package com.zhangsl.service.impl;

import com.zhangsl.entity.ProductInfo;
import com.zhangsl.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Zhangsl on 2018/1/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoServiceImpl mProductInfoService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = mProductInfoService.findOne("123465");
        Assert.assertEquals("123465",productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfos = mProductInfoService.findUpAll();
        Assert.assertNotEquals(0,productInfos.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> infoPage = mProductInfoService.findAll(pageRequest);
        System.out.println(infoPage.getTotalElements());
        Assert.assertNotEquals(0,infoPage.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo.Builder()
                .withProductId("123457")
                .withProductName("鱼粥")
                .withProductPrice(new BigDecimal(3.2))
                .withProductStock(100)
                .withProductDescription("很好喝的鱼粥哦")
                .withProductIcon("http://xxxx.jpg")
                .withProductStatus(ProductStatusEnum.DOWN.getCode())
                .withCategoryType(2)
                .build();
        ProductInfo result = mProductInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }

}