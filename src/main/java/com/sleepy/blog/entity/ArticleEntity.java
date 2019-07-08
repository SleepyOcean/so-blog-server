package com.sleepy.blog.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * 博客文章表
 *
 * @author Captain
 * @create 2019-04-14 14:28
 */
@Data
@Document(indexName = "so_blog", type = "so_article")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ArticleEntity {
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    private String title;

    private String content;

    private Date updateTime;

    private Date createTime;

    private String tags;
}
