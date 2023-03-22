package com.xw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author xw
 * @since 2023-03-21 22:54:41
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}

