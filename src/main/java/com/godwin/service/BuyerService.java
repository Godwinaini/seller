package com.godwin.service;

import com.godwin.dto.OrderDTO;

/**
 * 查询一个订单和取消订单的逻辑
 * Created by Godwin on 2021/1/28.
 */
public interface BuyerService {
    OrderDTO findOrderOne(String openid,String orderId);

    OrderDTO cancelOrder(String openid,String orderId);
}
