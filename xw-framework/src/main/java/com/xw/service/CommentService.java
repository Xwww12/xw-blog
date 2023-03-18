package com.xw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.Comment;

/**
 * 评论表(Comment)表服务接口
 *
 * @author xw
 * @since 2023-03-17 15:21:34
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize, Integer type);

    ResponseResult addComment(Comment comment);
}

