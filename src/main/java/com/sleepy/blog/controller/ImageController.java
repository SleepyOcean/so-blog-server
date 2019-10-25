package com.sleepy.blog.controller;

import com.sleepy.blog.service.CacheService;
import com.sleepy.blog.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/**
 * 图片服务Controller
 *
 * @author gehoubao
 * @create 2019-10-23 16:23
 **/
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/resource/img")
public class ImageController {
    @Autowired
    CacheService cacheService;

    @GetMapping(value = "/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
        String imgPath = cacheService.getCache("ImageLocalPath") + name;
        File file = new File(imgPath);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        inputStream.close();
        return bytes;
    }

    @GetMapping("/compress")
    public void getCompressedImage(HttpServletResponse response, @RequestParam("ratio") String ratio, @RequestParam("url") String url) {
        OutputStream outputStream = null;
        try {
            if (url != null) {
                URL path = new URL(StringUtil.formatURL(url));
                response.setContentType("image/jpeg");
                response.addHeader("Connection", "keep-alive");
                response.addHeader("Cache-Control", "max-age=604800");
                outputStream = response.getOutputStream();
                Thumbnails.of(path).scale(0.25f).outputFormat("jpeg").toOutputStream(outputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{} 获取图片失败！{} {}", "/compress请求", e.getMessage(), url);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("{} 流关闭失败！{}", "/compress请求", e.getMessage());
            }
        }
    }
}