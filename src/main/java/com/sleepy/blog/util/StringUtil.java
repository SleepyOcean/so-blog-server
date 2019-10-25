package com.sleepy.blog.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author gehoubao
 * @create 2019-04-25 13:44
 **/
public class StringUtil {
    public static final Pattern CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

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

    public static String formatURL(String url) throws UnsupportedEncodingException {
        url = URLDecoder.decode(url, "utf-8").replaceAll("&amp;", "&");
        String dest = url;
        Pattern pat = CHINESE_PATTERN;
        Matcher mat = pat.matcher(url);
        while (mat.find()) {
            String s = mat.group();
            dest = dest.replaceAll(s, URLEncoder.encode(s, "utf-8"));
        }
        return dest;
    }

    public static String getRandomUUID(String intervalMark) {
        return UUID.randomUUID().toString().replaceAll("-", intervalMark);
    }
}