package com.godwin.dto;

import lombok.Data;

/**
 * 购物车
 * Created by Godwin on 2021/1/16.
 */
@Data
public class CartDTO {
    /*商品Id*/
    private String productId;
    /*商品数量*/
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
