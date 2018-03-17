package com.zhangsl.controller;

import com.zhangsl.VO.ProductInfoVO;
import com.zhangsl.VO.ProductVO;
import com.zhangsl.VO.ResultVO;
import com.zhangsl.entity.ProductCategory;
import com.zhangsl.entity.ProductInfo;
import com.zhangsl.service.ProductCategoryService;
import com.zhangsl.service.ProductInfoService;
import com.zhangsl.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.BeanInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Zhangsl on 2018/1/28.
 */
@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService mProductInfoService;

    @Autowired
    private ProductCategoryService mProductCategoryService;

    @GetMapping(value = "/list")
    public ResultVO list(){
        //1. 查询所有的上架商品
        List<ProductInfo> productInfoList = mProductInfoService.findUpAll();

        //2.查询类目(一次性查询)
        //List<Integer> categoryTypeList = new ArrayList<>();

        //传统方法
        //for (ProductInfo productInfo : productInfoList) {
        //    categoryTypeList.add(productInfo.getCategoryType());
        //}


        //精简方法(java8,lambda)
        List<Integer> categoryTypeList =
                productInfoList.stream().
                map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = mProductCategoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            ArrayList<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    //拷贝对象属性
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        ResultVO resultVO = ResultVOUtil.success(productVOList);
        return resultVO;
    }
}
