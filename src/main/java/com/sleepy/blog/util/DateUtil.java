package com.sleepy.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理类
 *
 * @author gehoubao
 * @create 2019-04-26 10:20
 **/
public class DateUtil {

    /**
     * 格式化日期
     *
     * @param date
     * @return "yyyy-MM-dd HH:mm:ss"的字符串
     */
    public static String dateFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}