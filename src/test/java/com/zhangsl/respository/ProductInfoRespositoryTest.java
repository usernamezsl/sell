package com.zhangsl.respository;

import com.zhangsl.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Zhangsl on 2018/1/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRespositoryTest {

    @Autowired
    ProductInfoRespository mProductInfoRespository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo.Builder()
                .withProductId("123465")
                .withProductName("皮蛋粥")
                .withProductPrice(new BigDecimal(3.2))
                .withProductStock(100)
                .withProductDescription("很好喝的粥")
                .withProductIcon("http://xxxx.jpg")
                .withProductStatus(0)
                .withCategoryType(2)
                .build();
        ProductInfo result = mProductInfoRespository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatusTest() throws Exception {
        List<ProductInfo> productInfoList = mProductInfoRespository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }
}