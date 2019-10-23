package com.sleepy.blog.controller;

import com.sleepy.blog.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 图片服务Controller
 *
 * @author gehoubao
 * @create 2019-10-23 16:23
 **/
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
}