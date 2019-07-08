package com.sleepy.blog.dto;

import lombok.Data;

/**
 * 文章DTO
 *
 * @author gehoubao
 * @create 2019-07-08 16:17
 **/
@Data
public class PostDTO {
    private String id;

    private String title;

    private String content;

    private String updateTime;

    private String createTime;

    private String tags;
}