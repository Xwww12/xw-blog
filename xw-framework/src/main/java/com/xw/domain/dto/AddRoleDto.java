package com.xw.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddRoleDto {
    //角色名称
    private String roleName;

    //角色权限字符串
    private String roleKey;

    //显示顺序
    private Integer roleSort;

    //角色状态（0正常 1停用）
    private String status;

    private List<String> menuIds;

    //备注
    private String remark;
}
