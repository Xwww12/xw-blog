package com.xw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
}
