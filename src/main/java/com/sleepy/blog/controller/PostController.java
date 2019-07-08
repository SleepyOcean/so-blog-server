package com.sleepy.blog.controller;

import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.entity.ArticleEntity;
import com.sleepy.blog.service.PostService;
import com.sleepy.blog.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * 文章发布控制器
 *
 * @author Captain
 * @create 2019-04-20 13:25
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/save")
    public CommonDTO<String> save(@RequestBody PostVO vo) throws ParseException {
        return postService.saveArticle(vo);
    }

    @PostMapping("/get")
    public CommonDTO<ArticleEntity> get(@RequestBody PostVO vo) {
        return postService.getArticle(vo);
    }

    @PostMapping("/delete")
    public CommonDTO<ArticleEntity> delete(@RequestBody PostVO vo) {
        return postService.deleteArticle(vo);
    }
}
