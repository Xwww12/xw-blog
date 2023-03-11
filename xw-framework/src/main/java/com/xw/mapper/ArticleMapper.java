package com.xw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.domain.entity.Article;
import com.xw.domain.vo.ArticleDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> getHot10ArticleList();

    List<Article> getArticleAndCategoryName(@Param("pageNum") Integer pageNum, @Param("pageSize")Integer pageSize, @Param("categoryId")Long categoryId);

    ArticleDetailVo getArticleDetail(@Param("id") Long id);
}
