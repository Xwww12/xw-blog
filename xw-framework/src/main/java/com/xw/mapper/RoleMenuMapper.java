package com.xw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.domain.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色和菜单关联表(RoleMenu)表数据库访问层
 *
 * @author xw
 * @since 2023-03-23 20:59:54
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}

