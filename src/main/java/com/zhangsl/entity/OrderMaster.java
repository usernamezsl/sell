package com.zhangsl.entity;

import com.zhangsl.enums.OrderStatusEnum;
import com.zhangsl.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Zhangsl on 2018/1/29.
 * 订单主表
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    //订单id
    @Id
    private String orderId;

    //买家名字
    private String buyerName;

    //买家电话
    private String buyerPhone;

    //买家地址
    private String buyerAddress;

    //买家微信openid
    private String buyerOpenid;

    //订单总金额
    private BigDecimal orderAmount;

    //订单状态, 默认0为新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    //支付状态, 默认0未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();


    public OrderMaster() {
    }

    private OrderMaster(Builder builder) {
        setOrderId(builder.orderId);
        setBuyerName(builder.buyerName);
        setBuyerPhone(builder.buyerPhone);
        setBuyerAddress(builder.buyerAddress);
        setBuyerOpenid(builder.buyerOpenid);
        setOrderAmount(builder.orderAmount);
        setOrderStatus(builder.orderStatus);
        setPayStatus(builder.payStatus);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(OrderMaster copy) {
        Builder builder = new Builder();
        builder.orderId = copy.getOrderId();
        builder.buyerName = copy.getBuyerName();
        builder.buyerPhone = copy.getBuyerPhone();
        builder.buyerAddress = copy.getBuyerAddress();
        builder.buyerOpenid = copy.getBuyerOpenid();
        builder.orderAmount = copy.getOrderAmount();
        builder.orderStatus = copy.getOrderStatus();
        builder.payStatus = copy.getPayStatus();
        return builder;
    }


    public static final class Builder {
        private String orderId;
        private String buyerName;
        private String buyerPhone;
        private String buyerAddress;
        private String buyerOpenid;
        private BigDecimal orderAmount;
        private Integer orderStatus;
        private Integer payStatus;
        private Date createTime;
        private Date updateTime;

        public Builder() {
        }

        public Builder setOrderId(String val) {
            orderId = val;
            return this;
        }

        public Builder setBuyerName(String val) {
            buyerName = val;
            return this;
        }

        public Builder setBuyerPhone(String val) {
            buyerPhone = val;
            return this;
        }

        public Builder setBuyerAddress(String val) {
            buyerAddress = val;
            return this;
        }

        public Builder setBuyerOpenid(String val) {
            buyerOpenid = val;
            return this;
        }

        public Builder setOrderAmount(BigDecimal val) {
            orderAmount = val;
            return this;
        }

        public Builder setOrderStatus(Integer val) {
            orderStatus = val;
            return this;
        }

        public Builder setPayStatus(Integer val) {
            payStatus = val;
            return this;
        }

        public OrderMaster build() {
            return new OrderMaster(this);
        }
    }
}
