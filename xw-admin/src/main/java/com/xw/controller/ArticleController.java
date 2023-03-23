package com.xw.controller;

import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddArticleDto;
import com.xw.domain.dto.QueryArticleDto;
import com.xw.domain.entity.Article;
import com.xw.domain.vo.ArticleDetailVo;
import com.xw.service.ArticleService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public ResponseResult pageList(Integer pageNum, Integer pageSize, QueryArticleDto queryArticleDto) {
        return articleService.pageList(pageNum, pageSize, queryArticleDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getArticle(@PathVariable("id") Long id) {
        return articleService.getArticle(id);
    }

    @PutMapping()
    public ResponseResult updateArticle(@RequestBody AddArticleDto addArticleDto) {
        return articleService.updateArticle(addArticleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteArticle(@PathVariable("id") Long id) {
        return articleService.deleteArticle(id);
    }
}
