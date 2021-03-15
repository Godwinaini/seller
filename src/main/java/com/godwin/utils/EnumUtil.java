package com.godwin.utils;

import com.godwin.enums.CodeEnum;

/**
 * Created by admin on 2021/3/10.
 */
public class EnumUtil {
    public static <T extends CodeEnum>T getByCode(Integer code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
