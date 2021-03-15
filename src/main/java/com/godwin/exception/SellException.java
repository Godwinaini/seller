package com.godwin.exception;

import com.godwin.enums.ResultEnum;

/**
 * 自定义异常
 * Created by Godwin on 2021/1/27.
 */
public class SellException extends RuntimeException {

    /*错误码*/
    private Integer code;

    //传入自定义ResultEnum枚举
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());//把message传到父类的构造方法里面去
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
