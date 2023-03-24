package com.xw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddCategoryDto;
import com.xw.domain.entity.Article;
import com.xw.domain.entity.Category;
import com.xw.domain.entity.User;
import com.xw.domain.vo.CategoryVo;
import com.xw.domain.vo.PageVo;
import com.xw.exception.SystemException;
import com.xw.mapper.CategoryMapper;
import com.xw.service.ArticleService;
import com.xw.service.CategoryService;
import com.xw.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-03-09 21:47:52
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private ArticleService articleService;
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public ResponseResult getCategoryList() {
        // 获取所有正式发表的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        // 获取文章的分类id
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        // 根据分类id查询分类
        LambdaQueryWrapper<Category> categoryWrapper = new LambdaQueryWrapper<>();
        categoryWrapper
                .in(Category::getId, categoryIds)
                .eq(Category::getStatus, SystemConstants.CATEGORY_STATUS_NORMAL);
        List<Category> categoryList = list(categoryWrapper);

        // sql版
        // List<Category> categoryList = categoryMapper.getCategoryList();

        // 分装为vo
        List<CategoryVo> categoryVoList = BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class);

        return ResponseResult.okResult(categoryVoList);
    }

    @Override
    public ResponseResult pageList(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)) {
            wrapper.like(Category::getName, name);
        }
        if (StringUtils.hasText(status)) {
            wrapper.eq(Category::getStatus, status);
        }
        if (pageNum == null || pageNum < 0) {
            pageNum = SystemConstants.DEFAULT_PAGE_NUM;
        }
        if (pageSize == null || pageSize < 0) {
            pageSize = SystemConstants.DEFAULT_PAGE_SIZE;
        }
        Page<Category> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addCategory(AddCategoryDto categoryDto) {
        if (count(new LambdaQueryWrapper<Category>()
                .eq(Category::getName, categoryDto.getName())) > 0) {
            throw new SystemException(500, "分类名已存在");
        }
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        save(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getCategory(Long id) {
        if (id == null || id < 0) {
            throw new SystemException(500, "id异常");
        }
        Category category = getById(id);
        return ResponseResult.okResult(category);
    }

    @Override
    public ResponseResult updateCategory(Category category) {
        if (count(new LambdaQueryWrapper<Category>()
                .eq(Category::getId, category.getId())) == 0) {
            throw new SystemException(500, "分类不存在");
        }
        updateById(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteCategory(Long id) {
        if (articleService.count(new LambdaQueryWrapper<Article>()
                .eq(Article::getCategoryId, id)) > 0) {
            throw new SystemException(500, "分类下有文章关联，无法删除");
        }
        removeById(id);
        return ResponseResult.okResult();
    }
}

