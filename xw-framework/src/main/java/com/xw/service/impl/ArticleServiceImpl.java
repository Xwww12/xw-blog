package com.xw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.constants.RedisConstants;
import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddArticleDto;
import com.xw.domain.entity.Article;
import com.xw.domain.entity.ArticleTag;
import com.xw.domain.vo.ArticleDetailVo;
import com.xw.domain.vo.ArticleListVo;
import com.xw.domain.vo.HotArticleVo;
import com.xw.domain.vo.PageVo;
import com.xw.mapper.ArticleMapper;
import com.xw.service.ArticleService;
import com.xw.service.ArticleTagService;
import com.xw.service.CategoryService;
import com.xw.utils.BeanCopyUtils;
import com.xw.utils.RedisCache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private CategoryService categoryService;
    @Resource
    private RedisCache redisCache;

    @Resource
    private ArticleTagService articleTagService;

    @Override
    public ResponseResult hotArticleList() {
        // 1.非草稿、浏览量前10
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getViewCount);
        List<Article> articles = page(new Page<Article>(0, 10), wrapper).getRecords();
        // sql 版
        // List<Article> articles = articleMapper.getHot10ArticleList();

        // 2.分装为vo
        List<HotArticleVo> res = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(res);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        if (categoryId == 0)
            categoryId = null;
        if (pageNum == null || pageNum < 0)
            pageNum = SystemConstants.DEFAULT_PAGE_NUM;
        if (pageSize == null || pageSize < 0)
            pageSize = SystemConstants.DEFAULT_PAGE_SIZE;
        List<Article> articleList = articleMapper.getArticleAndCategoryName((pageNum - 1) * pageSize, pageSize, categoryId);
        // 转换为Vo
        List<ArticleListVo> articleListVoList = BeanCopyUtils.copyBeanList(articleList, ArticleListVo.class);
        // 封装到PageVo中
        PageVo pageVo = new PageVo(articleListVoList, (long) articleListVoList.size());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        if (id == null)
            return ResponseResult.okResult();
        ArticleDetailVo articleDetailVo = articleMapper.getArticleDetail(id);
        // 读取缓存的浏览量
        Integer viewCount = redisCache.getCacheMapValue(RedisConstants.VIEW_COUNT, id.toString());
        articleDetailVo.setViewCount(viewCount.longValue());

        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        redisCache.incrementCacheMapValue(RedisConstants.VIEW_COUNT, id.toString(), 1);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult addArticle(AddArticleDto articleDto) {
        // 保存文章
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);

        // 保存文章标签
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }
}
