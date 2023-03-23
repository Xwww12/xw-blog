package com.xw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.MenuDto;
import com.xw.domain.entity.Menu;
import com.xw.domain.vo.SelectMenuVo;
import com.xw.enums.AppHttpCodeEnum;
import com.xw.exception.SystemException;
import com.xw.mapper.MenuMapper;
import com.xw.service.MenuService;
import com.xw.utils.BeanCopyUtils;
import com.xw.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author xw
 * @since 2023-03-21 22:50:57
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    public List<String> selectPermsByUserId(Long id) {
        // 管理员返回所有权限
        if (id == 1L) {
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON)
                    .eq(Menu::getStatus, SystemConstants.LINK_STATUS_NORMAL);
            List<Menu> menus = list(wrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        // 通过用户id获取menu
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        if (SecurityUtils.isAdmin()) {
            menus = menuMapper.selectAllRouterMenu();
        } else {
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        // 构建菜单的树结构
        List<Menu> menuTree = builderMenuTree(menus, 0L);
        return menuTree;
    }

    @Override
    public ResponseResult menuList(MenuDto menuDto) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        String status = menuDto.getStatus();
        String menuName = menuDto.getMenuName();
        if (status != null && !status.equals("")) {
            wrapper.eq(Menu::getStatus, status);
        }
        if (menuName != null && !menuName.equals("")) {
            wrapper.like(Menu::getMenuName, menuName);
        }
        List<Menu> list = list(wrapper);

        return ResponseResult.okResult(list);
    }

    @Override
    public ResponseResult addMenu(Menu menu) {
        if (count(new LambdaQueryWrapper<Menu>().eq(Menu::getPath, menu.getPath())) > 0) {
            throw new SystemException(AppHttpCodeEnum.COMPONENT_IS_EXIST);
        }
        save(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getMenu(Long id) {
        if (id == null) {
            return ResponseResult.okResult();
        }
        Menu menu = getById(id);
        return ResponseResult.okResult(menu);
    }

    @Override
    public ResponseResult updateMenu(Menu menu) {
        if (menu.getParentId().equals(menu.getId())) {
            throw new SystemException(500, "修改菜单'写博文'失败，上级菜单不能选择自己");
        }
        List<Menu> list = list(new LambdaQueryWrapper<Menu>().eq(Menu::getPath, menu.getPath()));
        if (list.size() > 0 && !list.get(0).getId().equals(menu.getId())) {
            throw new SystemException(500, "路由已存在");
        }
        updateById(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenu(Long menuId) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId, menuId);
        if (count(wrapper) > 0) {
            throw new SystemException(500, "存在子菜单不允许删除");
        }
        removeById(menuId);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult treeSelect() {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = menuMapper.selectAllRouterMenu();
        // 构建菜单的树结构
        List<Menu> menuTree = builderMenuTree(menus, 0L);
        return ResponseResult.okResult(menuTree);
    }

    @Override
    public ResponseResult roleMenuTreeSelect(Long id) {
        HashMap<String, Object> map = new HashMap<>();
        MenuMapper menuMapper = getBaseMapper();
        // 构建菜单的树结构
        List<Menu> menus = menuMapper.selectAllRouterMenu();
        List<Menu> menuTree = builderMenuTree(menus, 0L);
        map.put("menus", menuTree);

        List<Menu> checked = null;
        if (id == 1L) {
            checked = menuMapper.selectAllRouterMenu();
        } else {
            checked = menuMapper.selectMenuByRoleId(id);
        }

        // 构建角色所拥有的菜单的id
        map.put("checkedKeys", checked.stream().map(c -> c.getId()).collect(Collectors.toList()));
        return ResponseResult.okResult(map);
    }

    private List<Menu> builderMenuTree(List<Menu> menus, long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
        return childrenList;
    }
}

