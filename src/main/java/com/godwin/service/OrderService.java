package com.godwin.service;

import com.github.pagehelper.Page;
import com.godwin.dto.OrderDTO;

import java.util.List;

/**
 * Created by Godwin on 2021/1/27.
 */
public interface OrderService {
    /*创建订单*/
    OrderDTO create(OrderDTO orderDTO);
    /*查询单个订单*/
    OrderDTO findOne(String orderId);
    /*取消订单*/
    OrderDTO cancel(OrderDTO orderDTO);
    /*完成订单*/
    OrderDTO finish(OrderDTO orderDTO);
    /*支付订单*/
    OrderDTO paid(OrderDTO orderDTO);
    /*根据buyerOpenid查询订单列表*/
    List<OrderDTO> findList(String buyerOpenid, int pageNum , int pageSize);
    //查询所有订单（分页）
    List<OrderDTO> findAll(int pageNum , int pageSize);



}
