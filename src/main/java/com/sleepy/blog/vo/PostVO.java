package com.sleepy.blog.vo;

import lombok.Data;

/**
 * 文章发布VO
 *
 * @author Captain
 * @create 2019-04-20 13:32
 */
@Data
public class PostVO {
    private String id;
    private String title;
    private String content;
    private String tags;
    private String date;
}
