package com.sleepy.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 测试
 * @author Captain
 * @create 2019-04-13 15:45
 */
@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "连接至后台";
    }
}
