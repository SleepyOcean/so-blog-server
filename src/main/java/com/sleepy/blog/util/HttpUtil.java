package com.sleepy.blog.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * http工具类
 *
 * @author gehoubao
 * @create 2019-08-27 19:33
 **/
public class HttpUtil {

    public static String sendPost(String url, String jsonParams) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;

        JSONObject params = JSON.parseObject(jsonParams);
        //解决中文乱码问题
        StringEntity entity = new StringEntity(params.toString(), "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        System.out.println();

        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
        }
        return respContent;
    }

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8092/haiyan-server/resource/vehicles/vehicle-concealed/analysis";
        String params = "{\"startOccTime\":\"2019-08-21 17:25:09\",\"endOccTime\":\"2019-08-28 17:25:09\",\"beforeRetrospectDay\":\"2\",\"beforeLimitTimes\":\"1\",\"afterRetrospectDay\":\"2\",\"afterLimitTimes\":\"1000\",\"deviceLimit\":[\"32050500011120000862\",\"32050500011120000934\",\"32050500011120000928\",\"32050500011120000930\",\"32050500011120000892\",\"32050500011120000856\",\"32050500011120000858\",\"32050500011120001024\"],\"userName\":\"gehoubao_hy\",\"searchFlag\":1,\"recordStartNo\":0,\"pageRecordNum\":30}";
        System.out.println(sendPost(url, params));
    }
}