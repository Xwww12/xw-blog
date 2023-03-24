package com.xw.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddCategoryDto;
import com.xw.domain.dto.AddUserDto;
import com.xw.domain.entity.Category;
import com.xw.domain.vo.CategoryVo;
import com.xw.domain.vo.ExcelCategoryVo;
import com.xw.enums.AppHttpCodeEnum;
import com.xw.service.CategoryService;
import com.xw.utils.BeanCopyUtils;
import com.xw.utils.WebUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        return ResponseResult.okResult(categoryService.list(wrapper));
    }

    @PreAuthorize("@ps.hasPermission('contet:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        try {
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet()
                    .doWrite(data());
        } catch (Exception e) {
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    /**
     * 获取所有分类
     * @return
     */
    private List<ExcelCategoryVo> data() {
        List<Category> categories = categoryService.list();
        return BeanCopyUtils.copyBeanList(categories, ExcelCategoryVo.class);
    }

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, String name, String status) {
        return categoryService.pageList(pageNum, pageSize, name, status);
    }

    @PostMapping()
    public ResponseResult addCategory(@RequestBody AddCategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getCategory(@PathVariable("id") Long id) {
        return categoryService.getCategory(id);
    }

    @PutMapping()
    public ResponseResult updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteCategory(@PathVariable("id") Long id) {
        return categoryService.deleteCategory(id);
    }
}
