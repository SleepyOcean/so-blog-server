package com.sleepy.blog.service.impl;

import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.entity.ProjectEntity;
import com.sleepy.blog.repository.ProjectRepository;
import com.sleepy.blog.service.ProjectService;
import com.sleepy.blog.util.StringUtil;
import com.sleepy.blog.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 项目管理服务实现类
 *
 * @author gehoubao
 * @create 2019-06-26 11:11
 **/
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public CommonDTO<String> saveProject(ProjectVO vo) {
        CommonDTO<String> result = new CommonDTO<>();
        ProjectEntity entity = new ProjectEntity();
        entity.setProjectName(vo.getProjectName());
        entity.setModuleName(vo.getModuleName());
        entity.setDeadline(vo.getDeadline());
        entity.setCreateTime(vo.getCreateTime());
        entity.setUpdateTime(vo.getUpdateTime());
        entity.setStatus(vo.getStatus());
        entity.setNote(StringUtil.isNullOrEmpty(vo.getNote()) ? "" : vo.getNote());
        entity.setDeleteFlag(0);
        projectRepository.save(entity).toString();
        result.setResult("success");
        return result;
    }

    @Override
    public CommonDTO<ProjectEntity> getProject(ProjectVO vo) {
        CommonDTO<ProjectEntity> result = new CommonDTO<>();
        List<ProjectEntity> sets = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(vo.getId())) {
            Optional<ProjectEntity> set = projectRepository.findById(vo.getId());
            sets.add(set.get());
        } else if (!StringUtil.isNullOrEmpty(vo.getProjectName())) {
            sets = projectRepository.findAllByProjectNameLike("%" + vo.getProjectName() + "%", new Sort(Sort.Direction.DESC, "deadline"));
        } else if (!StringUtil.isNullOrEmpty(vo.getModuleName())) {
            sets = projectRepository.findAllByModuleNameLike("%" + vo.getModuleName() + "%", new Sort(Sort.Direction.DESC, "deadline"));
        } else if (!StringUtil.isNullOrEmpty(vo.getDeadline())) {
            sets = projectRepository.findAllByDeadline(vo.getDeadline());
        } else if (null != vo.getStatus()) {
            sets = projectRepository.findAllByStatus(vo.getStatus(), new Sort(Sort.Direction.DESC, "deadline"));
        } else {
            sets = projectRepository.findAll(new Sort(Sort.Direction.DESC, "deadline"));
            result.setResultList(sets);
        }
        List<ProjectEntity> list = new ArrayList<>();
        sets.forEach(o -> {
            if (o.getDeleteFlag() == 0) {
                list.add(o);
            }
        });
        result.setResultList(list);
        return result;
    }

    @Override
    public CommonDTO<ProjectEntity> deleteProject(ProjectVO vo) {
        CommonDTO<ProjectEntity> result = new CommonDTO<>();
        Optional<ProjectEntity> entity = projectRepository.findById(vo.getId());
        entity.get().setDeleteFlag(1);
        projectRepository.saveAndFlush(entity.get());
        return result;
    }

    @Override
    public CommonDTO<String> updateStatus(ProjectVO vo) {
        CommonDTO<String> result = new CommonDTO<>();
        Optional<ProjectEntity> entity = projectRepository.findById(vo.getId());
        if (null != vo.getStatus()) {
            entity.get().setStatus(vo.getStatus());
        }
        if (!StringUtil.isNullOrEmpty(vo.getProjectName())) {
            entity.get().setProjectName(vo.getProjectName());
        }
        if (!StringUtil.isNullOrEmpty(vo.getModuleName())) {
            entity.get().setModuleName(vo.getModuleName());
        }
        if (!StringUtil.isNullOrEmpty(vo.getDeadline())) {
            entity.get().setDeadline(vo.getDeadline());
        }
        if (!StringUtil.isNullOrEmpty(vo.getNote())) {
            entity.get().setNote(vo.getNote());
        }
        if (!StringUtil.isNullOrEmpty(vo.getUpdateTime())) {
            entity.get().setUpdateTime(vo.getUpdateTime());
        }
        projectRepository.saveAndFlush(entity.get());
        result.setResult("success");
        return result;
    }
}