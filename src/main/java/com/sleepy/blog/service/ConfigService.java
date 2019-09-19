package com.sleepy.blog.service;

import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.entity.SettingEntity;
import com.sleepy.blog.vo.ConfigVO;

/**
 * 配置项Service
 *
 * @author ghb
 * @create 2019-09-04 16:54
 **/
public interface ConfigService {

    /**
     * 保存配置项
     *
     * @param vo
     * @return
     */
    CommonDTO<SettingEntity> save(ConfigVO vo);

    /**
     * 获取所有配置项
     *
     * @param vo
     * @return
     */
    CommonDTO<SettingEntity> findAllConfig(ConfigVO vo);

    /**
     * 获取配置项
     *
     * @param vo
     * @return
     */
    CommonDTO<SettingEntity> findConfig(ConfigVO vo);
}
