package com.xw.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.Category;
import com.xw.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult list() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        return ResponseResult.okResult(categoryService.list(wrapper));
    }
}
