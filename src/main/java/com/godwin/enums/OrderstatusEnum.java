package com.godwin.enums;

import lombok.Getter;

/**
 * 订单状态枚举
 * Created by Godwin on 2021/1/27.
 */
@Getter
public enum OrderstatusEnum implements CodeEnum{
    NEW(0,"新订单"),
    FINISHED(1,"完成"),
    CANCEL(2,"已取消")
    ;

    private Integer code;
    private String message;

    OrderstatusEnum() {
    }

    OrderstatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
