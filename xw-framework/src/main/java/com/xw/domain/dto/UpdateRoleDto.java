package com.xw.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateRoleDto {
    //角色ID
    private Long id;

    //备注
    private String remark;

    //角色权限字符串
    private String roleKey;

    //角色名称
    private String roleName;

    //显示顺序
    private Integer roleSort;

    //角色状态（0正常 1停用）
    private String status;

    List<String> menuIds;
}
