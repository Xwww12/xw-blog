package com.xw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.Category;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-03-09 21:47:51
 */
public interface CategoryService extends IService<Category> {

    /**
     * 查询分类列表
     * @return
     */
    ResponseResult getCategoryList();
}

