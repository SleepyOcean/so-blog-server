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
        projectRepository.save(entity).toString();
        result.setResult("success");
        return result;
    }

    @Override
    public CommonDTO<ProjectEntity> getProject(ProjectVO vo) {
        CommonDTO<ProjectEntity> result = new CommonDTO<>();
        if (!StringUtil.isNullOrEmpty(vo.getId())) {
            Optional<ProjectEntity> set = projectRepository.findById(vo.getId());
            result.setResult(set.get());
        } else if (!StringUtil.isNullOrEmpty(vo.getProjectName())) {
            List<ProjectEntity> sets = projectRepository.findAllByProjectNameLike("%" + vo.getProjectName() + "%", new Sort(Sort.Direction.DESC, "deadline"));
            result.setResultList(sets);
        } else if (!StringUtil.isNullOrEmpty(vo.getModuleName())) {
            List<ProjectEntity> sets = projectRepository.findAllByModuleNameLike("%" + vo.getModuleName() + "%", new Sort(Sort.Direction.DESC, "deadline"));
            result.setResultList(sets);
        } else if (!StringUtil.isNullOrEmpty(vo.getDeadline())) {
            List<ProjectEntity> sets = projectRepository.findAllByDeadline(vo.getDeadline());
            result.setResultList(sets);
        } else if (null != vo.getStatus()) {
            List<ProjectEntity> sets = projectRepository.findAllByStatus(vo.getStatus(), new Sort(Sort.Direction.DESC, "deadline"));
            result.setResultList(sets);
        } else {
            List<ProjectEntity> sets = projectRepository.findAll(new Sort(Sort.Direction.DESC, "deadline"));
            result.setResultList(sets);
        }
        return result;
    }

    @Override
    public CommonDTO<ProjectEntity> deleteProject(ProjectVO vo) {
        CommonDTO<ProjectEntity> result = new CommonDTO<>();
        projectRepository.deleteById(vo.getId());
        return result;
    }

    @Override
    public CommonDTO<String> updateStatus(ProjectVO vo) {
        CommonDTO<String> result = new CommonDTO<>();
        Optional<ProjectEntity> entity = projectRepository.findById(vo.getId());
        entity.get().setStatus(vo.getStatus());
        entity.get().setUpdateTime(vo.getUpdateTime());
        projectRepository.saveAndFlush(entity.get());
        result.setResult("success");
        return result;
    }
}