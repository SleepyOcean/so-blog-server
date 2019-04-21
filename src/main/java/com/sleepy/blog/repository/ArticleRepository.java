package com.sleepy.blog.repository;

import com.sleepy.blog.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ghb
 * @create 2019-04-19 15:19
 **/

public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {
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
