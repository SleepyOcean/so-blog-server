package com.sleepy.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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

    private String updateTime;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String tags;
}
