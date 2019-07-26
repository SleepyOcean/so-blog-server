package com.sleepy.blog.service;

import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.dto.PostDTO;
import com.sleepy.blog.entity.ArticleEntity;
import com.sleepy.blog.vo.PostVO;

import java.io.IOException;
import java.text.ParseException;

/**
 * 文章发布服务
 *
 * @author Captain
 * @create 2019-04-20 13:26
 */
public interface PostService {


    /**
     * 获取热门文章
     *
     * @param vo
     * @return
     * @throws IOException
     */
    CommonDTO<PostDTO> getHotArticle(PostVO vo) throws IOException;

    /**
     * 获取相关文章
     *
     * @param vo
     * @return
     * @throws IOException
     */
    CommonDTO<PostDTO> getRelatedArticle(PostVO vo) throws IOException;
    /**
     * 保存文章
     *
     * @param vo
     * @return
     * @throws ParseException
     */
    CommonDTO<String> saveArticle(PostVO vo) throws ParseException;

    /**
     * 搜索文章
     *
     * @param vo
     * @return
     * @throws IOException
     */
    CommonDTO<PostDTO> searchArticle(PostVO vo) throws IOException;

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

    /**
     * 获取tags
     *
     * @param vo
     * @return
     */
    CommonDTO<String> getTags(PostVO vo);
}
