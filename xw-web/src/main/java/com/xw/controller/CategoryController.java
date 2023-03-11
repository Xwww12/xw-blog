package com.xw.controller;

import com.xw.domain.ResponseResult;
import com.xw.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 分类表(Category)表控制层
 *
 * @author makejava
 * @since 2023-03-09 21:47:44
 */
@RestController
@RequestMapping("category")
public class CategoryController{
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList() {
        return categoryService.getCategoryList();
    }
}

