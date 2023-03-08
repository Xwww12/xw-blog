package com.xw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.Article;
import com.xw.domain.vo.HotArticleVo;
import com.xw.mapper.ArticleMapper;
import com.xw.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{
    @Resource
    private ArticleMapper articleMapper;

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
        List<HotArticleVo> res = new ArrayList<>();
        for (Article article : articles) {
            HotArticleVo vo = new HotArticleVo();
            BeanUtils.copyProperties(article, vo);
            res.add(vo);
        }
        return ResponseResult.okResult(res);
    }
}
