package com.godwin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品信息返回给前端层
 * Created by Godwin on 2021/1/27.
 */
@Data
public class ProductVo {
    /**
     * @JsonProperty("id")
     * 此注解用于属性上
     * 作用是把该属性的名称序列化为另一个名称返给前端展示
     */
    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description  ")
    private String productDescription;
    @JsonProperty("icon")
    private String productIcon;
}
