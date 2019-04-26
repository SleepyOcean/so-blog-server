package com.sleepy.blog.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 博客文章表
 *
 * @author Captain
 * @create 2019-04-14 14:28
 */
@Data
@Entity
@Table(name = "so_article")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ArticleEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "update_time")
    private String updateTime;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "tags")
    private String tags;
}