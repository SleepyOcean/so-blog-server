package com.sleepy.blog.service.impl;

import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.entity.SettingEntity;
import com.sleepy.blog.repository.SettingRepository;
import com.sleepy.blog.service.ConfigService;
import com.sleepy.blog.vo.ConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 配置项 ServiceImpl
 *
 * @author gehoubao
 * @create 2019-09-04 16:54
 **/
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    SettingRepository settingRepository;

    @Override
    public CommonDTO<SettingEntity> save(ConfigVO vo) {
        CommonDTO<SettingEntity> result = new CommonDTO<>();
        SettingEntity entity = new SettingEntity();
        entity.setConfigKey(vo.getKey());
        entity.setConfigValue(vo.getValue());
        entity.setConfigInfo(vo.getInfo());
        result.setResult(settingRepository.save(entity));
        return result;
    }

    @Override
    public CommonDTO<SettingEntity> findAllConfig(ConfigVO vo) {
        CommonDTO<SettingEntity> result = new CommonDTO<>();
        List<SettingEntity> set = settingRepository.findAll();
        result.setResultList(set);
        return result;
    }

    @Override
    public CommonDTO<SettingEntity> findConfig(ConfigVO vo) {
        CommonDTO<SettingEntity> result = new CommonDTO<>();
        // TODO 获取指定条件配置项
        return result;
    }
}