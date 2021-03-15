package com.godwin.dataobject;

import com.godwin.enums.OrderstatusEnum;
import com.godwin.enums.PayStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主表
 * Created by Godwin on 2021/1/27.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMaster {
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
    /*订单状态,默认为0新下单*/
    private Integer orderStatus = OrderstatusEnum.NEW.getCode();
    /*支付状态,默认为0未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;
}
