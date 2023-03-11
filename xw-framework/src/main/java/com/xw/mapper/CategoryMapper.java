package com.xw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-09 21:50:33
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    public List<Category> getCategoryList();
}

