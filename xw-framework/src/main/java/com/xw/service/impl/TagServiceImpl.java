package com.xw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.TagListDto;
import com.xw.domain.entity.Tag;
import com.xw.domain.vo.PageVo;
import com.xw.enums.AppHttpCodeEnum;
import com.xw.exception.SystemException;
import com.xw.mapper.TagMapper;
import com.xw.service.TagService;
import com.xw.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 标签(Tag)表服务实现类
 *
 * @author xw
 * @since 2023-03-21 18:28:49
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        if (pageNum == null || pageNum < 0) {
            pageNum = SystemConstants.DEFAULT_PAGE_NUM;
        }
        if (pageSize == null || pageSize < 0) {
            pageSize = SystemConstants.DEFAULT_PAGE_SIZE;
        }
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName())
                .eq(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());
        // 分页查询
        Page<Tag> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(TagListDto tagListDto) {
        if (tagListDto.getName() == null || tagListDto.getName().equals("")) {
            throw new SystemException(AppHttpCodeEnum.NAME_EXIST);
        }
        if (count(new LambdaQueryWrapper<Tag>().eq(Tag::getName, tagListDto.getName())) > 0) {
            throw new SystemException(AppHttpCodeEnum.NAME_EXIST);
        }
        Tag tag = new Tag();
        tag.setName(tagListDto.getName());
        tag.setRemark(tagListDto.getRemark());
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTag(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTag(Long id) {
        return ResponseResult.okResult(getById(id));
    }

    @Override
    public ResponseResult updateTag(Tag tag) {
        if (count(new LambdaQueryWrapper<Tag>().eq(Tag::getId, tag.getId())) == 0) {
            throw new SystemException(AppHttpCodeEnum.TAG_NOT_EXIST);
        }
        Tag one = getOne(new LambdaQueryWrapper<Tag>().eq(Tag::getName, tag.getName()));
        if (one != null && !one.getId().equals(tag.getId())) {
            throw new SystemException(AppHttpCodeEnum.NAME_EXIST);
        }
        updateById(tag);
        return ResponseResult.okResult();
    }
}

