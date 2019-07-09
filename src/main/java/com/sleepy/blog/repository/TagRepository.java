package com.sleepy.blog.repository;

import com.sleepy.blog.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ghb
 * @create 2019-07-09 19:18
 **/

public interface TagRepository extends JpaRepository<TagEntity, String> {

    /**
     * 获取所有tag名称
     *
     * @return
     */
    @Query(value = "select distinct tag_name from so_tag", nativeQuery = true)
    List<String> findAllTag();
}
