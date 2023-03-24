package com.xw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddLinkDto;
import com.xw.domain.entity.Article;
import com.xw.domain.entity.Category;
import com.xw.domain.entity.Link;
import com.xw.domain.vo.LinkVo;
import com.xw.domain.vo.PageVo;
import com.xw.exception.SystemException;
import com.xw.mapper.LinkMapper;
import com.xw.service.LinkService;
import com.xw.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author xw
 * @since 2023-03-11 18:09:09
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> linkWrapper = new LambdaQueryWrapper<>();
        linkWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> list = list(linkWrapper);
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(list, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public ResponseResult pageList(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)) {
            wrapper.like(Link::getName, name);
        }
        if (StringUtils.hasText(status)) {
            wrapper.eq(Link::getStatus, status);
        }
        if (pageNum == null || pageNum < 0) {
            pageNum = SystemConstants.DEFAULT_PAGE_NUM;
        }
        if (pageSize == null || pageSize < 0) {
            pageSize = SystemConstants.DEFAULT_PAGE_SIZE;
        }
        Page<Link> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addLink(AddLinkDto linkDto) {
        Link link = BeanCopyUtils.copyBean(linkDto, Link.class);
        save(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getLink(Long id) {
        if (id == null || id < 0) {
            throw new SystemException(500, "id异常");
        }
        Link link = getById(id);
        return ResponseResult.okResult(link);
    }

    @Override
    public ResponseResult updateLink(Link link) {
        if (count(new LambdaQueryWrapper<Link>()
                .eq(Link::getId, link.getId())) == 0) {
            throw new SystemException(500, "分类不存在");
        }
        updateById(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteLink(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}

