package com.xw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.domain.entity.Comment;
import com.xw.domain.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论表(Comment)表数据库访问层
 *
 * @author xw
 * @since 2023-03-17 15:21:34
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentVo> commentList(@Param("articleId") Long articleId,
                                @Param("pageNum") Integer pageNum,
                                @Param("pageSize") Integer pageSize,
                                @Param("type") Integer type);

    List<CommentVo> selectChildren(@Param("rootId") Long id);
}

