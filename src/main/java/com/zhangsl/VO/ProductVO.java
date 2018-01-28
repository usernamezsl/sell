package com.zhangsl.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品(包含类目)
 * Created by Zhangsl on 2018/1/28.
 */
@Data
public class ProductVO {

    //类目名称
    @JsonProperty(value = "name") //指定返回给前端的字段名称为 name
    private String categoryName;

    //类目编号
    @JsonProperty(value = "type") //指定返回给前端的字段名称为 type
    private Integer categoryType;

    @JsonProperty(value = "foods")
    private List<ProductInfoVO> productInfoVOList;

}
