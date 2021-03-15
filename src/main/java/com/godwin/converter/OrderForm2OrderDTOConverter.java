package com.godwin.converter;

import com.godwin.dto.OrderDTO;
import com.godwin.enums.ResultEnum;
import com.godwin.exception.SellException;
import com.godwin.form.OrderForm;
import com.godwin.dataobject.OrderDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 转换器
 * OrderForm转OrderDTO
 * Created by Godwin on 2021/1/28.
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){
        // Gson提供了fromJson()方法来实现从Json相关对象到Java实体的方法
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch (Exception e){
            log.error("【对象转换】 错误,string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //购物车是要转成list的orderDTO,但是items是一个字符，前端传过来是json格式
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
