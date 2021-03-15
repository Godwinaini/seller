package com.godwin.enums;

import lombok.Getter;

/**
 * 商品状态枚举类
 *
 * Created by Godwin on 2021/1/26.
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架"),
    ;

    private Integer code;
    private String message;

    ProductStatusEnum() {
    }

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
