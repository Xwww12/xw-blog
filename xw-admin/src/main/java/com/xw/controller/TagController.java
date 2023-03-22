package com.xw.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.TagListDto;
import com.xw.domain.entity.Tag;
import com.xw.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    @GetMapping("/listAllTag")
    public ResponseResult list() {
        return ResponseResult.okResult(tagService.list());
    }

    @PostMapping()
    public ResponseResult addTag(@RequestBody TagListDto tagListDto) {
        return tagService.addTag(tagListDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getTag(@PathVariable Long id) {
        return tagService.getTag(id);
    }

    @PutMapping()
    public ResponseResult updateTag(@RequestBody Tag tag) {
        return tagService.updateTag(tag);
    }
}
