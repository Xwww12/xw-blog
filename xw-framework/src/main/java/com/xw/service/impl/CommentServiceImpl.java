package com.xw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.Comment;
import com.xw.domain.vo.CommentVo;
import com.xw.domain.vo.PageVo;
import com.xw.enums.AppHttpCodeEnum;
import com.xw.exception.SystemException;
import com.xw.mapper.CommentMapper;
import com.xw.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author xw
 * @since 2023-03-17 15:21:34
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize, Integer type) {
        if (articleId <= 0)
            articleId = null;
        if (pageNum == null || pageNum < 0)
            pageNum = SystemConstants.DEFAULT_PAGE_NUM;
        if (pageSize == null || pageSize < 0)
            pageSize = SystemConstants.DEFAULT_PAGE_SIZE;

        // 查询文章的根评论
        List<CommentVo> list = commentMapper.commentList(articleId, (pageNum - 1) * pageSize, pageSize, type);
        // 查询根评论下的子评论
        for (CommentVo commentVo : list) {
            List<CommentVo> children = commentMapper.selectChildren(commentVo.getId());
            commentVo.setChildren(children);
        }

        // 查询评论总数
        LambdaQueryWrapper<Comment> commentWrapper = new LambdaQueryWrapper<>();
        commentWrapper.eq(Comment::getArticleId, articleId).eq(Comment::getRootId, -1).eq(Comment::getType, type);
        int count = count(commentWrapper);

        return ResponseResult.okResult(new PageVo(list, (long) count));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }
}

