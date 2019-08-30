package com.sleepy.blog.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理类
 *
 * @author gehoubao
 * @create 2019-04-26 10:20
 **/
public class DateUtil {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化日期
     *
     * @param date
     * @return "yyyy-MM-dd HH:mm:ss"的字符串
     */
    public static String dateFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 字符串转日期
     *
     * @param dateString
     * @param dateFormat
     * @return
     */
    public static Date toDate(String dateString, String dateFormat) {
        Date date = null;
        try {
            date = new SimpleDateFormat(dateFormat).parse(dateString);
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    public static Date getDateWithCurrent(int amount, int unit) {
        Calendar c = Calendar.getInstance();
        c.add(unit, amount);
        return c.getTime();
    }
}