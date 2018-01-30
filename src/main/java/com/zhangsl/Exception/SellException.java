package com.zhangsl.Exception;

import com.zhangsl.enums.ResultEnum;

/**
 * Created by Zhangsl on 2018/1/29.
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();
    }

}
