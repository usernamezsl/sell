package com.zhangsl.service.impl;

import com.sun.xml.internal.bind.v2.TODO;
import com.zhangsl.Exception.SellException;
import com.zhangsl.converter.OrderMaster2OrderDTOConverter;
import com.zhangsl.dto.CartDTO;
import com.zhangsl.dto.OrderDto;
import com.zhangsl.entity.OrderDetail;
import com.zhangsl.entity.OrderMaster;
import com.zhangsl.entity.ProductInfo;
import com.zhangsl.enums.OrderStatusEnum;
import com.zhangsl.enums.PayStatusEnum;
import com.zhangsl.enums.ResultEnum;
import com.zhangsl.respository.OrderDetailRespository;
import com.zhangsl.respository.OrderMasterRespository;
import com.zhangsl.service.OrderMasterService;
import com.zhangsl.service.ProductInfoService;
import com.zhangsl.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhangsl on 2018/1/29.
 */
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterRespository mOrderMasterRespository;

    @Autowired
    private OrderDetailRespository mOrderDetailRespository;

    @Autowired
    private ProductInfoService mProductInfoService;

    @Override
    @Transactional //异常事物回滚
    public OrderDto create(OrderDto orderDto) {

        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        orderDto.setOrderId(orderId);
        //List<CartDTO> cartDTOList = new ArrayList<>();

        //1.查询商品(数量,价格)
        for (OrderDetail orderDetail : orderDto.getOrderDetails()) {
            ProductInfo productInfo = mProductInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }

            //2.计算订单总价格
           orderAmount =  productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            //拷贝属性
            BeanUtils.copyProperties(productInfo,orderDetail);
            OrderDetail result = mOrderDetailRespository.save(orderDetail);

            //CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            //cartDTOList.add(cartDTO);
        }
        //3.写入订单数据库(orderMaster和orderDetail)
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        mOrderMasterRespository.save(orderMaster);

        //4.下单成功 扣库存
        List<CartDTO> cartDTOList = orderDto.getOrderDetails().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        mProductInfoService.decreaseStock(cartDTOList);

        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = mOrderMasterRespository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        List<OrderDetail> orderDetailList = mOrderDetailRespository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIT);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetails(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = mOrderMasterRespository.findByBuyerOpenid(buyerOpenId,pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        Page<OrderDto> orderDTOPage = new PageImpl<OrderDto>(orderDtoList,pageable,orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();


        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            //打印日志
            // TODO: 2018/1/30 打印日志 待处理
            System.out.println("【取消订单】 订单状态不正确,orderId={},orderStatus={}");
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = mOrderMasterRespository.save(orderMaster);
        if (updateResult == null) {
            // TODO: 2018/1/30 打印日志 "【取消订单】 更新失败，打印出对象信息 orderMaster={}"
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetails())) {
            // TODO: 2018/1/30  打印日志 "【取消订单】 订单中无商品详情，打印出对象信息 orderDTO={}"
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDto.getOrderDetails().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        mProductInfoService.increaseStock(cartDTOList);

        //如果已支付,需退款
        if (orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            // TODO: 2018/1/30
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            //打印日志
            // TODO: 2018/1/30 打印日志 待处理
            System.out.println("【完结订单】 订单状态不正确,orderId={},orderStatus={}");
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = mOrderMasterRespository.save(orderMaster);
        if (updateResult == null) {
            // TODO: 2018/1/30 打印日志 "【完结订单】 更新失败，打印出对象信息 orderMaster={}"
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {

        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            //打印日志
            // TODO: 2018/1/30 打印日志 待处理
            System.out.println("【订单支付完成】 订单状态不正确,orderId={},orderStatus={}");
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            //打印日志
            // TODO: 2018/1/30 打印日志 待处理
            System.out.println("【订单支付完成】 订单支付状态不正确,orderId={},orderStatus={}");
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改订单支付状态
        orderDto.setPayStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = mOrderMasterRespository.save(orderMaster);
        if (updateResult == null) {
            // TODO: 2018/1/30 打印日志 "【订单支付完成】 更新失败，打印出对象信息 orderMaster={}"
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }
}
