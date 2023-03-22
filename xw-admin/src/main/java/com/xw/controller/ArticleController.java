package com.xw.controller;

import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddArticleDto;
import com.xw.service.ArticleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping
    public ResponseResult addArticle(@RequestBody AddArticleDto articleDto) {
        return articleService.addArticle(articleDto);
    }
}
