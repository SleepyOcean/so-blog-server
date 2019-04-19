package com.sleepy.blog.repository;

import com.sleepy.blog.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ghb
 * @create 2019-04-19 15:19
 **/

public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {
}
