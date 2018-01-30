package com.zhangsl.controller;

import com.zhangsl.Exception.SellException;
import com.zhangsl.VO.ResultVO;
import com.zhangsl.converter.OrderForm2OrderDtoConverter;
import com.zhangsl.dto.OrderDto;
import com.zhangsl.enums.ResultEnum;
import com.zhangsl.form.OrderForm;
import com.zhangsl.service.BuyerService;
import com.zhangsl.service.OrderMasterService;
import com.zhangsl.utils.ResultVOUtil;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhangsl on 2018/1/30.
 */
@RestController
@RequestMapping(value = "/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderMasterService mOrderMasterService;

    @Autowired
    private BuyerService mBuyerService;

    //创建订单
    @PostMapping(value = "/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            // TODO: 2018/1/30 打印log【创建表单】参数不正确 orderForm={}
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderForm2OrderDtoConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetails())) {
            // TODO: 2018/1/30 打印log【创建表单】购物车不能为空
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto createResult = mOrderMasterService.create(orderDto);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping(value = "/list")
    public ResultVO<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)) {
            // TODO: 2018/1/30 打印日志【查询订单列表】,openid为空
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDto> orderDtoPage = mOrderMasterService.findList(openid, pageRequest);

        return ResultVOUtil.success(orderDtoPage.getContent());
    }
    //查询订单详情
    @GetMapping(value = "/detail")
    public ResultVO<OrderDto> detail(@RequestParam(value = "openid") String openid,
                                     @RequestParam(value = "orderId") String orderId){
        // TODO: 2018/1/30 不安全的做法，改进;
        OrderDto orderDto = mBuyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDto);
    }
    //取消订单
    @PostMapping(value = "/cancel")
    public ResultVO cancel(@RequestParam(value = "openid") String openid,
                             @RequestParam(value = "orderId") String orderId){

        // TODO: 2018/1/30  不安全的做法，改进
        mBuyerService.cancelOrder(openid,orderId);
        return ResultVOUtil.success();
    }
}
