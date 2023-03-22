package com.xw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author xw
 * @since 2023-03-21 22:50:57
 */
public interface MenuService extends IService<Menu> {


    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}

