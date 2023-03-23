package com.xw.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xw.domain.entity.Menu;

import java.util.List;

public class SelectMenuVo {
    //菜单ID
    private Long id;

    //菜单名称
    private String menuName;

    //父菜单ID
    private Long parentId;

    @TableField(exist = false)
    private List<Menu> children;
}
