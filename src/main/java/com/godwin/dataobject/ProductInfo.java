package com.godwin.dataobject;

import com.godwin.enums.ProductStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息实体类
 * Created by Godwin on 2021/1/26.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    /*商品Id*/
    private String productId;
    /*商品名字*/
    private String productName;
    /*商品单价*/
    private BigDecimal productPrice;
    /*商品库存*/
    private Integer productStock;
    /*商品描述*/
    private String productDescription;
    /*商品小图*/
    private String productIcon;
    /*商品状态 ,0在架；1下架*/
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    /*类目编号*/
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
