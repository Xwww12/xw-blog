package com.xw.controller;

import com.xw.domain.ResponseResult;
import com.xw.domain.dto.MenuDto;
import com.xw.domain.entity.Menu;
import com.xw.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/system/menu")
public class SystemMenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult list(MenuDto menuDto) {
        return menuService.menuList(menuDto);
    }

    @PostMapping()
    public ResponseResult addMenu(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }

    @GetMapping("/{id}")
    public ResponseResult getMenu(@PathVariable("id") Long id) {
        return menuService.getMenu(id);
    }

    @PutMapping()
    public ResponseResult updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{menuId}")
    public ResponseResult deleteMenu(@PathVariable("menuId") Long menuId) {
        return menuService.deleteMenu(menuId);
    }

    @GetMapping("/treeselect")
    public ResponseResult treeSelect() {
        return menuService.treeSelect();
    }

    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult roleMenuTreeSelect(@PathVariable("id") Long id) {
        return menuService.roleMenuTreeSelect(id);
    }
}
