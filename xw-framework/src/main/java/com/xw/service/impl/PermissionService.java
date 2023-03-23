package com.xw.service.impl;

import com.xw.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ps")
public class PermissionService {

    /**
     * 判断用户是否具有权限
     * @param permission
     * @return
     */
    public boolean hasPermission(String permission) {
        // 超级管理员
        if (SecurityUtils.isAdmin()) {
            return true;
        }

        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}
