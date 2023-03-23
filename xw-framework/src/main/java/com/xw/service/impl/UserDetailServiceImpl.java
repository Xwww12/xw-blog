package com.xw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xw.constants.SystemConstants;
import com.xw.domain.entity.LoginUser;
import com.xw.domain.entity.User;
import com.xw.mapper.UserMapper;
import com.xw.service.MenuService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 获取用户身份信息的方法，替换默认的SpringSecurity的获取身份信息方式
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, username));
        if (Objects.isNull(user)) {
            throw new RuntimeException("账号或密码错误");
        }

        // 查询用户权限信息
        List<String> permissions = new ArrayList<>();
        if (user.getType().equals(SystemConstants.ADMIN)) {
            // 只有后台管理员需要鉴权
            permissions = menuService.selectPermsByUserId(user.getId());
        }
        // 返回用户信息
        return new LoginUser(user, permissions);
    }
}
