package com.zhangsl.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by Zhangsl on 2018/1/28.
 */
@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;

    //商品名称
    private String productName;

    //单价
    private BigDecimal productPrice;

    //库存
    private Integer productStock;

    //描述
    private String productDescription;

    //小图
    private String productIcon;

    //商品状态,0正常1下架
    private Integer productStatus;

    //类目编号
    private Integer categoryType;

    public ProductInfo() {
    }

    private ProductInfo(Builder builder) {
        setProductId(builder.productId);
        setProductName(builder.productName);
        setProductPrice(builder.productPrice);
        setProductStock(builder.productStock);
        setProductDescription(builder.productDescription);
        setProductIcon(builder.productIcon);
        setProductStatus(builder.productStatus);
        setCategoryType(builder.categoryType);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ProductInfo copy) {
        Builder builder = new Builder();
        builder.productId = copy.getProductId();
        builder.productName = copy.getProductName();
        builder.productPrice = copy.getProductPrice();
        builder.productStock = copy.getProductStock();
        builder.productDescription = copy.getProductDescription();
        builder.productIcon = copy.getProductIcon();
        builder.productStatus = copy.getProductStatus();
        builder.categoryType = copy.getCategoryType();
        return builder;
    }


    public static final class Builder {
        private String productId;
        private String productName;
        private BigDecimal productPrice;
        private Integer productStock;
        private String productDescription;
        private String productIcon;
        private Integer productStatus;
        private Integer categoryType;

        public Builder() {
        }

        public Builder withProductId(String val) {
            productId = val;
            return this;
        }

        public Builder withProductName(String val) {
            productName = val;
            return this;
        }

        public Builder withProductPrice(BigDecimal val) {
            productPrice = val;
            return this;
        }

        public Builder withProductStock(Integer val) {
            productStock = val;
            return this;
        }

        public Builder withProductDescription(String val) {
            productDescription = val;
            return this;
        }

        public Builder withProductIcon(String val) {
            productIcon = val;
            return this;
        }

        public Builder withProductStatus(Integer val) {
            productStatus = val;
            return this;
        }

        public Builder withCategoryType(Integer val) {
            categoryType = val;
            return this;
        }

        public ProductInfo build() {
            return new ProductInfo(this);
        }
    }
}
