package com.sleepy.blog.repository;

import com.sleepy.blog.entity.ArticleEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ghb
 * @create 2019-04-19 15:19
 **/
@Repository
public interface ArticleRepository extends ElasticsearchRepository<ArticleEntity, String> {
    /**
     * 通过title查找记录
     *
     * @param title
     * @return
     */
    List<ArticleEntity> findAllByTitle(String title);

    /**
     * 通过title模糊查找记录
     *
     * @param title
     * @return
     */
    List<ArticleEntity> findAllByTitleLike(String title);
}
