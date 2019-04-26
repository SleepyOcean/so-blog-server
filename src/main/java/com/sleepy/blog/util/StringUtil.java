package com.sleepy.blog.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 字符串工具类
 *
 * @author gehoubao
 * @create 2019-04-25 13:44
 **/
public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param string
     * @return
     */
    public static boolean isNullOrEmpty(String string) {
        if (null == string || "".equals(string)) {
            return true;
        }
        return false;
    }

    /**
     * 美化json字符串
     *
     * @param json
     * @return 美化后的json字符串
     * @throws IOException
     */
    public static String formatJSON(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = null;
        obj = mapper.readValue(json, Object.class);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

}