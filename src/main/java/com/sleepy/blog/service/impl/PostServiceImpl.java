package com.sleepy.blog.service.impl;

import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.entity.ArticleEntity;
import com.sleepy.blog.repository.ArticleRepository;
import com.sleepy.blog.service.PostService;
import com.sleepy.blog.util.StringUtil;
import com.sleepy.blog.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 文章发布服务接口实现
 *
 * @author Captain
 * @create 2019-04-20 13:26
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    ArticleRepository articleRepository;

    @Override
    public CommonDTO<String> saveArticle(PostVO vo) {
        CommonDTO<String> result = new CommonDTO<>();
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(vo.getTitle());
        entity.setContent(vo.getContent());
        entity.setCreateTime(vo.getDate());
        entity.setTags(vo.getTags());
        articleRepository.save(entity).toString();
        result.setResult("success");
        return result;
    }

    @Override
    public CommonDTO<ArticleEntity> getArticle(PostVO vo) {
        CommonDTO<ArticleEntity> result = new CommonDTO<>();
        if (!StringUtil.isNullOrEmpty(vo.getId())) {
            Optional<ArticleEntity> set = articleRepository.findById(vo.getId());
            result.setResult(set.get());
        } else {
            List<ArticleEntity> sets = articleRepository.findAllByTitleLike("%" + vo.getTitle() + "%");
            result.setResultList(sets);
        }
        return result;
    }

    @Override
    public CommonDTO<ArticleEntity> deleteArticle(PostVO vo) {
        CommonDTO<ArticleEntity> result = new CommonDTO<>();
        articleRepository.deleteById(vo.getId());
        return result;
    }
}
