package com.xw.init;

import com.xw.constants.RedisConstants;
import com.xw.domain.entity.Article;
import com.xw.mapper.ArticleMapper;
import com.xw.utils.RedisCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private RedisCache redisCache;

    /**
     * 预热文章的浏览量
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        redisCache.setCacheMap(RedisConstants.VIEW_COUNT, viewCountMap);
    }
}
