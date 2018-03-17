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

    /**
     * 查询一条商品数据
     * @param productInfoId
     * @return
     */
    @Override
    public ProductInfo findOne(String productInfoId) {
        ProductInfo productInfo = mProductInfoRespository.findOne(productInfoId);
        return productInfo;
    }

    /**
     * 查询所有已上架的商品
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        List<ProductInfo> productInfoList = mProductInfoRespository.findByProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoList;
    }

    /**
     * 分页查询所有
     * @param pageable
     * @return
     */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        Page<ProductInfo> productInfos = mProductInfoRespository.findAll(pageable);
        return productInfos;
    }

    /**
     * 新增 更新
     * @param productInfo
     * @return
     */
    @Override
    public ProductInfo save(ProductInfo productInfo) {
        ProductInfo result = mProductInfoRespository.save(productInfo);
        return result;
    }

    /**
     * 加库存
     * @param cartDTOS
     */
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

    /**
     * 减库存
     * @param cartDTOS
     */
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
