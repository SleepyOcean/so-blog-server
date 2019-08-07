package com.sleepy.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.dto.PostDTO;
import com.sleepy.blog.entity.ArticleEntity;
import com.sleepy.blog.entity.TagEntity;
import com.sleepy.blog.repository.ArticleRepository;
import com.sleepy.blog.repository.TagRepository;
import com.sleepy.blog.service.PostService;
import com.sleepy.blog.util.DateUtil;
import com.sleepy.blog.util.StringUtil;
import com.sleepy.blog.vo.PostVO;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 文章发布服务接口实现
 *
 * @author Captain
 * @create 2019-04-20 13:26
 */
@Service
@Slf4j
public class PostServiceImpl implements PostService {
    public static final String INDEX_NAME = "so_blog";
    public static final String TYPE_NAME = "so_article";

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    JestClient jestClient;


    @Override
    public CommonDTO<PostDTO> getHotArticle(PostVO vo) throws IOException {
        CommonDTO<PostDTO> result = new CommonDTO<>();
        String[] includes = {"id", "title", "readCount"};
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource(includes, null);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.sort("readCount", SortOrder.DESC);
        searchSourceBuilder.size(vo.getSize());
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX_NAME).addType(TYPE_NAME).build();
        JestResult jestResult = jestClient.execute(search);

        List<PostDTO> resultList = JSON.parseObject(JSON.toJSONString(jestResult.getValue("hits"))).getJSONArray("hits").stream().map(hit -> {
            PostDTO item = new PostDTO();
            JSONObject hitObj = JSON.parseObject(hit.toString());
            item.setId(hitObj.getString("_id"));
            item.setTitle(hitObj.getJSONObject("_source").getString("title"));
            item.setReadCount(hitObj.getJSONObject("_source").getLongValue("readCount"));
            return item;
        }).collect(Collectors.toList());

        result.setResultList(resultList);
        return result;
    }

    @Override
    public CommonDTO<PostDTO> getRelatedArticle(PostVO vo) throws IOException {
        List<String> articleIds = tagRepository.findArticleIdsByTags(vo.getTags().split(","));

        CommonDTO<PostDTO> result = new CommonDTO<>();
        String[] includes = {"id", "title", "summary"};
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource(includes, null);
        searchSourceBuilder.query(QueryBuilders.termsQuery("_id", articleIds));
        searchSourceBuilder.sort("readCount", SortOrder.DESC);
        searchSourceBuilder.size(vo.getSize());
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX_NAME).addType(TYPE_NAME).build();
        JestResult jestResult = jestClient.execute(search);

        List<PostDTO> resultList = JSON.parseObject(JSON.toJSONString(jestResult.getValue("hits"))).getJSONArray("hits").stream().map(hit -> {
            PostDTO item = new PostDTO();
            JSONObject hitObj = JSON.parseObject(hit.toString());
            item.setId(hitObj.getString("_id"));
            item.setTitle(hitObj.getJSONObject("_source").getString("title"));
            item.setSummary(hitObj.getJSONObject("_source").getString("summary"));
            return item;
        }).collect(Collectors.toList());

        result.setResultList(resultList);
        return result;
    }

    @Override
    public CommonDTO<String> saveArticle(PostVO vo) throws ParseException {
        CommonDTO<String> result = new CommonDTO<>();
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(vo.getTitle());
        entity.setContent(vo.getContent());
        entity.setCreateTime(DateUtil.toDate(vo.getDate(), DateUtil.DEFAULT_DATETIME_PATTERN));
        entity.setTags(vo.getTags());
        articleRepository.index(entity);

        // 存储文章标签
        String[] tags = vo.getTags().split(",");
        for (int i = 0; i < tags.length; i++) {
            TagEntity tag = new TagEntity();
            tag.setTagName(tags[i]);
            tag.setArticleId(entity.getId());
            tagRepository.save(tag);
        }
        result.setResult("success");

        return result;
    }

    @Override
    public CommonDTO<PostDTO> searchArticle(PostVO vo) throws IOException {
        CommonDTO<PostDTO> result = new CommonDTO<>();
        String[] includes = {"id", "title"};
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource(includes, null);
        searchSourceBuilder.query(QueryBuilders.matchQuery("content", vo.getKeyword()));
        searchSourceBuilder.sort("_score", SortOrder.DESC);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("content");
        highlightBuilder.preTags("<span style='color: #ffa500;font-weight: bold;font-size: 16px !important;'>").postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX_NAME).addType(TYPE_NAME).build();
        JestResult jestResult = jestClient.execute(search);

        List<PostDTO> resultList = JSON.parseObject(JSON.toJSONString(jestResult.getValue("hits"))).getJSONArray("hits").stream().map(hit -> {
            PostDTO item = new PostDTO();
            JSONObject hitObj = JSON.parseObject(hit.toString());
            item.setId(hitObj.getString("_id"));
            item.setTitle(hitObj.getJSONObject("_source").getString("title"));
            item.setSearchResult(hitObj.getJSONObject("highlight").getJSONArray("content").toJavaList(String.class));
            return item;
        }).collect(Collectors.toList());

        result.setResultList(resultList);
        return result;
    }

    @Override
    public CommonDTO<ArticleEntity> getArticle(PostVO vo) {
        CommonDTO<ArticleEntity> result = new CommonDTO<>();
        if (!StringUtil.isNullOrEmpty(vo.getId())) {
            Optional<ArticleEntity> set = articleRepository.findById(vo.getId());
            result.setResult(set.get());
        } else if (!StringUtil.isNullOrEmpty(vo.getTitle())) {
            List<ArticleEntity> sets = articleRepository.findAllByTitleLike("%" + vo.getTitle() + "%");
            result.setResultList(sets);
            result.setTotal(Integer.valueOf(sets.size()).longValue());
        } else if (null != vo.getSize() && null != vo.getStart()) {
            Pageable pageable = PageRequest.of(vo.getStart(), vo.getSize(), new Sort(Sort.Direction.DESC, "createTime"));
            Page<ArticleEntity> sets = articleRepository.findAll(pageable);
            result.setTotal(sets.getTotalElements());
            result.setResultList(sets.getContent());
        } else {
            Iterable<ArticleEntity> sets = articleRepository.findAll();
            result.setResultList(Lists.newArrayList(sets));
        }
        return result;
    }

    @Override
    public CommonDTO<ArticleEntity> deleteArticle(PostVO vo) {
        CommonDTO<ArticleEntity> result = new CommonDTO<>();
        articleRepository.deleteById(vo.getId());
        return result;
    }

    @Override
    public CommonDTO<String> getTags(PostVO vo) {
        CommonDTO<String> result = new CommonDTO<>();
        result.setResultList(tagRepository.findAllTag());
        return result;
    }
}
