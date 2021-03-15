package com.godwin.mapper;



import com.godwin.dataobject.OrderMaster;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单主表
 * Created by Godwin on 2021/1/27.
 */
@Repository
public interface OrderMasterMapper {
    /*根据orderId查询*/
    OrderMaster findByOrderId(String orderId);
    /*新增*/
    int add(OrderMaster orderMaster);
    //修改
    int update(OrderMaster orderMaster);
    //根据buyerOpenid查询该卖家购买所有
    List<OrderMaster> findByBuyerOpenid(@Param("buyerOpenid") String buyerOpenid);
    //查询所有(分页)
    List<OrderMaster> getQuery();
}
