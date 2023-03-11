package com.xw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;

/**
 * 友链(Link)表数据库访问层
 *
 * @author xw
 * @since 2023-03-11 18:09:09
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}

