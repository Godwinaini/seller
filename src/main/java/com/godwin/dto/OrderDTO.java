package com.godwin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.godwin.dataobject.OrderDetail;
import com.godwin.enums.OrderstatusEnum;
import com.godwin.enums.PayStatusEnum;
import com.godwin.serializer.Date2LongSerializer;
import com.godwin.utils.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


import java.util.Date;
import java.util.List;

/**
 * OrderMaster的DTO，用于关联OrderDetail
 *
 * OrderMaster的数据必有一条或者多条对应到OrderDetail,直接用OrderMaster的DTO不方便controller调用
 * 因为OrderMaster实体类里没有orderDetailList这个字段，所以不可以直接用！虽然可以在属性上加注解@Transent在数据库调用时忽视这个属性，但是时间长太乱了，代码不整洁
 * Created by Godwin on 2021/1/27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    /*订单Id*/
    private String orderId;
    /*买家名字*/
    private String buyerName;
    /*买家手机号*/
    private String buyerPhone;
    /*买家地址*/
    private String buyerAddress;
    /*买家微信Openid*/
    private String buyerOpenid;
    /*订单总金额*/
    private BigDecimal orderAmount;
    /*订单状态*/
    private Integer orderStatus;
    /*支付状态*/
    private Integer payStatus;
    /*创建时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /*更新时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    /*订单详情列表*/
    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public  OrderstatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderstatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
