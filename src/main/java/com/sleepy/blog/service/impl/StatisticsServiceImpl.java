package com.sleepy.blog.service.impl;

import com.sleepy.blog.dto.ChartOfBarDTO;
import com.sleepy.blog.dto.CommonDTO;
import com.sleepy.blog.service.StatisticsService;
import com.sleepy.blog.util.DateUtil;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 统计ServiceImpl
 *
 * @author gehoubao
 * @create 2019-08-29 19:28
 **/
@Service
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {
    public static final String INDEX_NAME = "so_blog";
    public static final String ARTICLE_TYPE_NAME = "so_article";
    @Autowired
    JestClient jestClient;

    @Override
    public CommonDTO<ChartOfBarDTO> getArticleStatistics() throws Exception {
        String startTime = DateUtil.dateFormat(DateUtil.getDateWithCurrent(-1, Calendar.MONTH));
        String endTime = DateUtil.dateFormat(new Date());

        CommonDTO<ChartOfBarDTO> result = new CommonDTO<>();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.rangeQuery("createTime").from(startTime).to(endTime));
        searchSourceBuilder.size(0);
        searchSourceBuilder.aggregation(AggregationBuilders.dateHistogram("week")
                .field("createTime").dateHistogramInterval(DateHistogramInterval.WEEK).format("yyyy-MM-dd").minDocCount(0));
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX_NAME).addType(ARTICLE_TYPE_NAME).build();
        JestResult jestResult = jestClient.execute(search);

        List<ChartOfBarDTO> resultList = ((SearchResult) jestResult).getAggregations().getTermsAggregation("week").getBuckets().stream().map(e -> {
            ChartOfBarDTO item = new ChartOfBarDTO();
            item.setXAxis(e.getKeyAsString());
            item.setYAxis(e.getCount().toString());
            return item;
        }).collect(Collectors.toList());

        Map<String, Object> extra = new HashMap<>(2);
        extra.put("xAxis", resultList.stream().map(o -> o.getXAxis()).collect(Collectors.toList()));
        extra.put("yAxis", resultList.stream().map(o -> o.getYAxis()).collect(Collectors.toList()));
        result.setExtra(extra);
        return result;
    }
}