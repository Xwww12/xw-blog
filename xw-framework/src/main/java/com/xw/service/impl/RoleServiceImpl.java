package com.xw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddRoleDto;
import com.xw.domain.dto.UpdateRoleDto;
import com.xw.domain.entity.Article;
import com.xw.domain.entity.Role;
import com.xw.domain.entity.RoleMenu;
import com.xw.domain.vo.PageVo;
import com.xw.domain.vo.RoleVo;
import com.xw.exception.SystemException;
import com.xw.mapper.RoleMapper;
import com.xw.mapper.RoleMenuMapper;
import com.xw.service.MenuService;
import com.xw.service.RoleMenuService;
import com.xw.service.RoleService;
import com.xw.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author xw
 * @since 2023-03-21 22:54:41
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        if (id == 1L) {
            ArrayList<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult pageList(Integer pageNum, Integer pageSize, String roleName, String status) {
        if (pageNum == null || pageNum < 0)
            pageNum = SystemConstants.DEFAULT_PAGE_NUM;
        if (pageSize == null || pageSize < 0)
            pageSize = SystemConstants.DEFAULT_PAGE_SIZE;

        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();

        if (roleName != null && !roleName.equals("")) {
            wrapper.like(Role::getRoleName, roleName);
        }
        if (status != null && !status.equals("")) {
            wrapper.like(Role::getStatus, status);
        }
        wrapper.orderByAsc(Role::getRoleSort);

        Page<Role> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult changeStatus(RoleVo roleVo) {
        Role role = getById(roleVo.getRoleId());
        if (role == null) {
            throw new SystemException(500, "角色不存在");
        }
        role.setStatus(roleVo.getStatus());
        updateById(role);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult addRole(AddRoleDto addRoleDto) {
        Role role = BeanCopyUtils.copyBean(addRoleDto, Role.class);
        save(role);
        List<RoleMenu> roleMenus = addRoleDto.getMenuIds().stream()
                .map(id -> new RoleMenu(role.getId(), Long.parseLong(id)))
                .collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenus);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getRole(Long id) {
        Role role = getById(id);
        return ResponseResult.okResult(role);
    }

    @Override
    @Transactional
    public ResponseResult updateRole(UpdateRoleDto updateRoleDto) {
        Role role = BeanCopyUtils.copyBean(updateRoleDto, Role.class);
        updateById(role);
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, role.getId()));
        List<RoleMenu> roleMenus = updateRoleDto.getMenuIds().stream()
                .map(id -> new RoleMenu(role.getId(), Long.parseLong(id)))
                .collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenus);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult deleteRole(Long id) {
        removeById(id);
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, id));
        return ResponseResult.okResult();
    }
}

