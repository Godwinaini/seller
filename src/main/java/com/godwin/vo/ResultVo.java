package com.godwin.vo;

import lombok.Data;

/**
 * http请求返回最外层
 * Created by Godwin on 2021/1/27.
 */
@Data
public class ResultVo<T>{
    /* 错误码 */
    private Integer code;
    /* 提示信息 */
    private String msg;
    /* 具体内容 */
    private T data;
}
