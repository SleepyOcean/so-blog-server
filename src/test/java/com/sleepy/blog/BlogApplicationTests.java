package com.sleepy.blog;

import com.sleepy.blog.entity.ArticleEntity;
import com.sleepy.blog.entity.TagEntity;
import com.sleepy.blog.repository.ArticleRepository;
import com.sleepy.blog.repository.TagRepository;
import com.sleepy.blog.util.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    TagRepository tagRepository;

    @Test
    public void contextLoads() {
        try {
            getArticleFromMeituan();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getArticle() throws IOException {
        String url = "https://yq.aliyun.com";
        Document doc = Jsoup.connect(url + "/articles").get();
        List<String> articleUrls = doc.getElementsByClass("item-box")
                .stream().map(o -> o.getElementsByTag("a").get(0).attr("href")).collect(Collectors.toList());

        for (int i = 0; i < articleUrls.size(); i++) {
            Document articleDoc = Jsoup.connect(url + articleUrls.get(i) + "?type=2").get();
            System.out.println("good");
            System.out.println(articleUrls.get(i));
            ArticleEntity entity = new ArticleEntity();
            entity.setTitle(articleDoc.getElementsByClass("blog-title").html());
            entity.setCreateTime(DateUtil.toDate(articleDoc.getElementsByClass("b-time").html(), DateUtil.DEFAULT_DATETIME_PATTERN));
            List<String> tags = articleDoc.getElementsByClass("label-item").stream().map(o -> o.getElementsByTag("span").html()).collect(Collectors.toList());
            StringBuilder tagStr = new StringBuilder();
            tags.forEach(o -> {
                tagStr.append(o);
                tagStr.append(",");
            });
            entity.setTags(tagStr.substring(0, tagStr.length() - 1));
            entity.setContent(articleDoc.getElementsByClass("markdown-body").html());
            entity.setSummary(Jsoup.parse(Jsoup.connect("https://yq.aliyun.com/articles/708486?type=2").get().getElementsByClass("markdown-body").get(0).getElementsByTag("p").get(0).html()).text());
            entity.setReadCount(Long.parseLong(articleDoc.getElementsByClass("b-watch").html().substring(2)));
            entity.setSource("转载：云栖社区：url + articleUrls.get(i) + \"?type=2\"");
            articleRepository.index(entity);
            tags.forEach(o -> {
                TagEntity tag = new TagEntity();
                tag.setTagName(o);
                tag.setArticleId(entity.getId());
                tagRepository.save(tag);
            });
        }
    }

    private void getArticleFromMeituan() throws IOException {
        String url = "https://tech.meituan.com/page/";
        for (int j = 1; j < 18; j++) {
            Document doc = Jsoup.connect(url + j + ".html").get();
            List<String> articleUrls = doc.getElementsByClass("post-title")
                    .stream().map(o -> o.getElementsByTag("a").get(0).attr("href")).collect(Collectors.toList());

            for (int i = 0; i < articleUrls.size(); i++) {
                Document articleDoc = Jsoup.connect(articleUrls.get(i)).get();
                System.out.println("loading meituan article");
                System.out.println(articleUrls.get(i));
                ArticleEntity entity = new ArticleEntity();
                entity.setTitle(articleDoc.getElementsByClass("post-title").get(0).getElementsByTag("a").html());
                String date = Jsoup.parse(articleDoc.getElementsByClass("m-post-date").get(0).html()).text();
                String[] dateStrs = date.split("[\\u4e00-\\u9fa5]");
                StringBuilder createTime = new StringBuilder();
                createTime.append(dateStrs[0]);
                createTime.append("-");
                createTime.append(dateStrs[1]);
                createTime.append("-");
                createTime.append(dateStrs[0]);
                createTime.append(" 00:00:00");
                entity.setCreateTime(DateUtil.toDate(createTime.toString(), DateUtil.DEFAULT_DATETIME_PATTERN));
                String[] tags = articleDoc.getElementsByClass("tag-links").stream().map(o -> o.getElementsByTag("a").html()).collect(Collectors.toList()).get(0).split("\n");
                StringBuilder tagStr = new StringBuilder();
                for (String o : tags) {
                    tagStr.append(o);
                    tagStr.append(",");
                }
                entity.setTags(tagStr.substring(0, tagStr.length() - 1));
                entity.setContent(articleDoc.getElementsByClass("content").html());
                entity.setSummary(Jsoup.parse(articleDoc.getElementsByClass("content").get(0).getElementsByTag("p").get(0).html()).text());
                entity.setReadCount(0L);
                entity.setSource("转载：美团技术团队：" + articleUrls.get(i));
                articleRepository.index(entity);
                for (String o : tags) {
                    TagEntity tag = new TagEntity();
                    tag.setTagName(o);
                    tag.setArticleId(entity.getId());
                    tagRepository.save(tag);
                }
            }
        }
    }
}
