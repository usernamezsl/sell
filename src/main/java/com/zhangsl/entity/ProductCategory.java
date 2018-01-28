package com.zhangsl.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by zhangsl on 2018/1/28.
 */
@Entity //映射数据表
@DynamicUpdate //动态更新
@Data //lombok自动生成get set toString 方法
public class ProductCategory {

    /**
     * 类目id
     */
    @Id
    @GeneratedValue //id 自增
    private Integer categoryId;

     /* *
     *类目名字
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
