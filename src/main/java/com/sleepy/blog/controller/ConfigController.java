package com.sleepy.blog.controller;

import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.entity.SettingEntity;
import com.sleepy.blog.service.ConfigService;
import com.sleepy.blog.util.StringUtil;
import com.sleepy.blog.vo.ConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 配置项操作 Controller
 *
 * @author gehoubao
 * @create 2019-09-04 16:51
 **/
@RestController
@CrossOrigin
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    ConfigService configService;

    @PostMapping("/save")
    public CommonDTO<SettingEntity> save(@RequestBody ConfigVO vo) {
        return configService.save(vo);
    }

    @PostMapping("/get")
    public CommonDTO<SettingEntity> get(@RequestBody ConfigVO vo) {
        if (StringUtil.isNullOrEmpty(vo.getId())) {
            return configService.findAllConfig(vo);
        } else {
            return configService.findConfig(vo);
        }
    }
}