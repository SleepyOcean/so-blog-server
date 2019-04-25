package com.sleepy.blog.service;

import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.entity.ArticleEntity;
import com.sleepy.blog.vo.PostVO;

/**
 * 文章发布服务
 *
 * @author Captain
 * @create 2019-04-20 13:26
 */
public interface PostService {

    /**
     * 保存文章
     *
     * @param vo
     * @return
     */
    CommonDTO<String> saveArticle(PostVO vo);

    /**
     * 获取文章
     *
     * @param vo
     * @return
     */
    CommonDTO<ArticleEntity> getArticle(PostVO vo);

}
