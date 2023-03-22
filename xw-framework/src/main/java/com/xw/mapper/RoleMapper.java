package com.xw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author xw
 * @since 2023-03-21 22:54:40
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}

