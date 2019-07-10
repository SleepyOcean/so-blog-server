package com.sleepy.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("博客文章表")
public class ArticleEntity {
    @GeneratedValue(generator = "jpa-uuid")
    @ApiModelProperty("博文ID")
    private String id;

    @ApiModelProperty("博客标题")
    private String title;

    @ApiModelProperty("博文摘要")
    private String summary;

    @ApiModelProperty("博文内容")
    private String content;

    @ApiModelProperty("最后一次更新时间")
    private String updateTime;

    @ApiModelProperty("博文创建时间")
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("博文标签")
    private String tags;

    @ApiModelProperty("阅读数")
    private Long readCount;

    @ApiModelProperty("评论数")
    private Long commentCount;

    @ApiModelProperty("转发数")
    private Long shareCount;

    @ApiModelProperty("热度")
    private Long hotRate;

    @ApiModelProperty("专栏")
    private String collection;

    @ApiModelProperty("文章来源 -> 【原创 | 转载：网站名称：url】")
    private String source;

    @ApiModelProperty("博客私密设置，0：公开， 1：私密")
    private Integer privacy;
}
