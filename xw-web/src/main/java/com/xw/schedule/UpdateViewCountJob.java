package com.xw.schedule;

import com.xw.constants.RedisConstants;
import com.xw.domain.entity.Article;
import com.xw.service.ArticleService;
import com.xw.utils.RedisCache;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class UpdateViewCountJob {
    @Resource
    private RedisCache redisCache;

    @Resource
    private ArticleService articleService;

    /**
     * 每十分钟更新一次浏览量
     */
    @Scheduled(cron = "0 0/10 * * * *")
    public void updateViewCount() {
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(RedisConstants.VIEW_COUNT);

        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        articleService.updateBatchById(articles);
    }
}
