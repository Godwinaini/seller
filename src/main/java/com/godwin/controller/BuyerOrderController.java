package com.godwin.controller;

import com.github.pagehelper.PageHelper;
import com.godwin.converter.OrderForm2OrderDTOConverter;
import com.godwin.dto.OrderDTO;
import com.godwin.enums.ResultEnum;
import com.godwin.exception.SellException;
import com.godwin.form.OrderForm;
import com.godwin.service.BuyerService;
import com.godwin.service.OrderService;
import com.godwin.utils.ResultVoUtil;
import com.godwin.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Godwin on 2021/1/28.
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        //先判断表单校验后有没有错误
        if (bindingResult.hasErrors()){
            log.error("【创建订单】 参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());//getDefaultMessage是在OrderForm里自己定义的@NotEmpty
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        //判断购物车是否为空
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVoUtil.success(map);
    }

    @GetMapping("/list")
    public ResultVo<OrderDTO> list(@RequestParam("openid") String openid,
                                   @RequestParam(value = "page",defaultValue = "0") Integer page,
                                   @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        List<OrderDTO> orderDTOList = orderService.findList(openid, page, size);
        return ResultVoUtil.success(orderDTOList);
    }
    //订单列表
    //订单详情(查询单个订单)
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("openid")String openid,
                                     @RequestParam("orderId")String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtil.success(orderDTO);

    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid")String openid,
                           @RequestParam("orderId")String orderId){
        buyerService.cancelOrder(openid,orderId);
        return ResultVoUtil.success();
    }




}
