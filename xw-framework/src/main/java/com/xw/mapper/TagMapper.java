package com.xw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.domain.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签(Tag)表数据库访问层
 *
 * @author xw
 * @since 2023-03-21 18:28:47
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}

