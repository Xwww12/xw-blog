package com.xw.controller;

import com.xw.domain.ResponseResult;
import com.xw.service.LinkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 友链(Link)表控制层
 *
 * @author xw
 * @since 2023-03-11 18:09:09
 */
@RestController
@RequestMapping("/link")
public class LinkController{
    @Resource
    private LinkService linkService;

    @GetMapping("/getAllLink")
    public ResponseResult getAllLink() {
        return linkService.getAllLink();
    }
}

