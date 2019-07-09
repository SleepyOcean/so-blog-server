package com.sleepy.blog.service.impl;

import com.google.common.collect.Lists;
import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.entity.ArticleEntity;
import com.sleepy.blog.entity.TagEntity;
import com.sleepy.blog.repository.ArticleRepository;
import com.sleepy.blog.repository.TagRepository;
import com.sleepy.blog.service.PostService;
import com.sleepy.blog.util.DateUtil;
import com.sleepy.blog.util.StringUtil;
import com.sleepy.blog.vo.PostVO;
import io.searchbox.client.JestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * 文章发布服务接口实现
 *
 * @author Captain
 * @create 2019-04-20 13:26
 */
@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    JestClient jestClient;


    @Override
    public CommonDTO<String> saveArticle(PostVO vo) throws ParseException {
        CommonDTO<String> result = new CommonDTO<>();
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(vo.getTitle());
        entity.setContent(vo.getContent());
        entity.setCreateTime(DateUtil.toDate(vo.getDate(), DateUtil.DEFAULT_DATETIME_PATTERN));
        entity.setTags(vo.getTags());
        articleRepository.index(entity);

        // 存储文章标签
        String[] tags = vo.getTags().split(",");
        for (int i = 0; i < tags.length; i++) {
            TagEntity tag = new TagEntity();
            tag.setTagName(tags[i]);
            tag.setArticleId(entity.getId());
            tagRepository.save(tag);
        }
        result.setResult("success");

        return result;
    }

    @Override
    public CommonDTO<ArticleEntity> getArticle(PostVO vo) {
        CommonDTO<ArticleEntity> result = new CommonDTO<>();
        if (!StringUtil.isNullOrEmpty(vo.getId())) {
            Optional<ArticleEntity> set = articleRepository.findById(vo.getId());
            result.setResult(set.get());
        } else if (!StringUtil.isNullOrEmpty(vo.getTitle())) {
            List<ArticleEntity> sets = articleRepository.findAllByTitleLike("%" + vo.getTitle() + "%");
            result.setResultList(sets);
        } else {
            Iterable<ArticleEntity> sets = articleRepository.findAll(new Sort(Sort.Direction.DESC, "createTime"));
            result.setResultList(Lists.newArrayList(sets));
        }
        return result;
    }

    @Override
    public CommonDTO<ArticleEntity> deleteArticle(PostVO vo) {
        CommonDTO<ArticleEntity> result = new CommonDTO<>();
        articleRepository.deleteById(vo.getId());
        return result;
    }

    @Override
    public CommonDTO<String> getTags(PostVO vo) {
        CommonDTO<String> result = new CommonDTO<>();
        result.setResultList(tagRepository.findAllTag());
        return result;
    }
}
