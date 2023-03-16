package com.xw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表(User)表数据库访问层
 *
 * @author xw
 * @since 2023-03-14 23:06:31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

