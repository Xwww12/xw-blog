package com.xw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.domain.entity.Role;
import com.xw.mapper.RoleMapper;
import com.xw.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author xw
 * @since 2023-03-21 22:54:41
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        if (id == 1L) {
            ArrayList<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        return getBaseMapper().selectRoleKeyByUserId(id);
    }
}

