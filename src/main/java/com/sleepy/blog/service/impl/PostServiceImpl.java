package com.sleepy.blog.service.impl;

import com.sleepy.blog.PostVO;
import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.entity.ArticleEntity;
import com.sleepy.blog.repository.ArticleRepository;
import com.sleepy.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        articleRepository.save(entity).toString();
        result.setResult("success");
        return result;
    }

    @Override
    public CommonDTO<ArticleEntity> getArticle(PostVO vo) {
        CommonDTO<ArticleEntity> result = new CommonDTO<>();
        List<ArticleEntity> sets = articleRepository.findAllByTitleLike("%" + vo.getTitle() + "%");
        result.setResultList(sets);
        result.setResult(sets.get(0));
        return result;
    }
}
