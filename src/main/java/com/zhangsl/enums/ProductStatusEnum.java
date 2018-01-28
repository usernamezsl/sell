package com.zhangsl.enums;

import lombok.Getter;

/**
 * 商品状态 枚举
 * Created by Zhangsl on 2018/1/28.
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"已上架"),
    DOWN(1,"已下架")
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
