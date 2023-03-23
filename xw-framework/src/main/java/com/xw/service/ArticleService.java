package com.xw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddArticleDto;
import com.xw.domain.dto.QueryArticleDto;
import com.xw.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addArticle(AddArticleDto articleDto);

    ResponseResult pageList(Integer pageNum, Integer pageSize, QueryArticleDto queryArticleDto);

    ResponseResult getArticle(Long id);

    ResponseResult updateArticle(AddArticleDto article);

    ResponseResult deleteArticle(Long id);
}
