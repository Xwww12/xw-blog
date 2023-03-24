package com.xw.controller;

import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddLinkDto;
import com.xw.domain.entity.Link;
import com.xw.service.LinkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, String name, String status) {
        return linkService.pageList(pageNum, pageSize, name, status);
    }

    @PostMapping()
    public ResponseResult addLink(@RequestBody AddLinkDto linkDto) {
        return linkService.addLink(linkDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getLink(@PathVariable("id") Long id) {
        return linkService.getLink(id);
    }

    @PutMapping()
    public ResponseResult updateLink(@RequestBody Link link) {
        return linkService.updateLink(link);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteLink(@PathVariable("id") Long id) {
        return linkService.deleteLink(id);
    }
}
