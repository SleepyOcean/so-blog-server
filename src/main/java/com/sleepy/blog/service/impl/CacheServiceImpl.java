package com.sleepy.blog.service.impl;

import com.sleepy.blog.entity.SettingEntity;
import com.sleepy.blog.repository.SettingRepository;
import com.sleepy.blog.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存ServiceImpl
 *
 * @author gehoubao
 * @create 2019-09-04 15:48
 **/
@Service
@Slf4j
public class CacheServiceImpl implements CacheService {
    private static Map<String, Object> cacheMap;

    private static Map<String, String> settingCache;

    static {
        cacheMap = new HashMap<>();
        settingCache = new HashMap<>();
    }

    @Autowired
    SettingRepository settingRepository;


    @Override
    public void setCache(String key, String value) {

    }

    @Override
    public String getCache(String key) {
        return null;
    }

    @Override
    public void setSettingCache() {
        log.info("开始缓存设置表");
        List<SettingEntity> result = settingRepository.findAll();
        result.forEach(e -> {
            settingCache.put(e.getConfigKey(), e.getConfigValue());
        });
        log.info("缓存设置表完成");
    }
}