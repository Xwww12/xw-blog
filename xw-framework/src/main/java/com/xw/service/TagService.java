package com.xw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.TagListDto;
import com.xw.domain.entity.Tag;

/**
 * 标签(Tag)表服务接口
 *
 * @author xw
 * @since 2023-03-21 18:28:48
 */
public interface TagService extends IService<Tag> {

    ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(TagListDto tagListDto);

    ResponseResult deleteTag(Long id);

    ResponseResult getTag(Long id);

    ResponseResult updateTag(Tag tag);
}

