package com.xw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddRoleDto;
import com.xw.domain.dto.UpdateRoleDto;
import com.xw.domain.entity.Role;
import com.xw.domain.vo.RoleVo;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author xw
 * @since 2023-03-21 22:54:41
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    ResponseResult pageList(Integer pageNum, Integer pageSize, String roleName, String status);

    ResponseResult changeStatus(RoleVo roleVo);

    ResponseResult addRole(AddRoleDto addRoleDto);

    ResponseResult getRole(Long id);

    ResponseResult updateRole(UpdateRoleDto updateRoleDto);

    ResponseResult deleteRole(Long id);

    ResponseResult listAllRole();
}

