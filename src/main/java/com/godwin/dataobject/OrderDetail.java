package com.godwin.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 订单详情实体类
 * Created by Godwin on 2021/1/27.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private String detailId;
    /*订单Id*/
    private String orderId;
    /*商品Id*/
    private String productId;
    /*商品名称*/
    private String productName;
    /*商品单价*/
    private BigDecimal productPrice;
    /*商品数量*/
    private Integer productQuantity;
    /*商品小图*/
    private String productIcon;
}
