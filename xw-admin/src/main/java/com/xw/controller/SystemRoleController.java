package com.xw.controller;

import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddArticleDto;
import com.xw.domain.dto.AddRoleDto;
import com.xw.domain.dto.MenuDto;
import com.xw.domain.dto.UpdateRoleDto;
import com.xw.domain.entity.Role;
import com.xw.domain.vo.RoleVo;
import com.xw.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/system/role")
public class SystemRoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, String roleName, String status) {
        return roleService.pageList(pageNum, pageSize, roleName, status);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleVo roleVo) {
        return roleService.changeStatus(roleVo);
    }

    @PostMapping()
    public ResponseResult addRole(@RequestBody AddRoleDto addRoleDto) {
        return roleService.addRole(addRoleDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getRole(@PathVariable("id") Long id) {
        return roleService.getRole(id);
    }

    @PutMapping()
    public ResponseResult updateRole(@RequestBody UpdateRoleDto updateRoleDto) {
        return roleService.updateRole(updateRoleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteRole(@PathVariable("id") Long id) {
        return roleService.deleteRole(id);
    }
}
