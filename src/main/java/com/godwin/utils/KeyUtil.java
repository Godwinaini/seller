package com.godwin.utils;

import java.util.Random;

/**
 * 生成随机数的方法
 * 给orderId和detailId使用，因为这两个不是自增
 * Created by Godwin on 2021/1/27.
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    //虽然是毫秒级别，又给加了个随机数，但为了防止多线程并发时，可能还会重复，所以要加synchronized
    public static synchronized String genUniqueKey(){
        //当前的毫秒数:System.currentTimeMillis()
        //生成的随机数永远是6位：random.nextInt(900000) + 100000
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
