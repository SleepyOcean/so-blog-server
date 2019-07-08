package com.sleepy.blog.service;

import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.entity.ArticleEntity;
import com.sleepy.blog.vo.PostVO;

import java.text.ParseException;

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
     * @throws ParseException
     */
    CommonDTO<String> saveArticle(PostVO vo) throws ParseException;

    /**
     * 获取文章
     *
     * @param vo
     * @return
     */
    CommonDTO<ArticleEntity> getArticle(PostVO vo);

    /**
     * 删除文章
     *
     * @param vo
     * @return
     */
    CommonDTO<ArticleEntity> deleteArticle(PostVO vo);
}
