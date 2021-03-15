package com.godwin.mapper;

import com.godwin.dataobject.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单详情
 * Created by Godwin on 2021/1/27.
 */
@Repository
public interface OrderDetailMapper {
    /*根据orderId查询*/
    List<OrderDetail> findByOrderId(String orderId);
    /*新增*/
    int add(OrderDetail orderDetail);
    //修改
    int update(OrderDetail orderDetail);
}
