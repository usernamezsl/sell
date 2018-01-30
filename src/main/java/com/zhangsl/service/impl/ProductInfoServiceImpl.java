package com.zhangsl.service.impl;

import com.zhangsl.Exception.SellException;
import com.zhangsl.dto.CartDTO;
import com.zhangsl.entity.ProductInfo;
import com.zhangsl.enums.ProductStatusEnum;
import com.zhangsl.enums.ResultEnum;
import com.zhangsl.respository.ProductInfoRespository;
import com.zhangsl.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Zhangsl on 2018/1/28.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoRespository mProductInfoRespository;

    @Override
    public ProductInfo findOne(String productInfoId) {
        ProductInfo productInfo = mProductInfoRespository.findOne(productInfoId);
        return productInfo;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        List<ProductInfo> productInfoList = mProductInfoRespository.findByProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoList;
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        Page<ProductInfo> productInfos = mProductInfoRespository.findAll(pageable);
        return productInfos;
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        ProductInfo result = mProductInfoRespository.save(productInfo);
        return result;
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOS) {
        for (CartDTO cartDTO : cartDTOS) {
            ProductInfo productInfo = mProductInfoRespository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            mProductInfoRespository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOS) {
        for (CartDTO cartDTO : cartDTOS) {
            ProductInfo productInfo = mProductInfoRespository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0){
                throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            mProductInfoRespository.save(productInfo);
        }
    }
}
