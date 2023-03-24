package com.xw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddLinkDto;
import com.xw.domain.entity.Link;

/**
 * 友链(Link)表服务接口
 *
 * @author xw
 * @since 2023-03-11 18:09:09
 */
public interface LinkService extends IService<Link> {

    /**
     * 获取所有友链
     * @return
     */
    ResponseResult getAllLink();

    ResponseResult pageList(Integer pageNum, Integer pageSize, String name, String status);

    ResponseResult addLink(AddLinkDto linkDto);

    ResponseResult getLink(Long id);

    ResponseResult updateLink(Link link);

    ResponseResult deleteLink(Long id);
}

