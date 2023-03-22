package com.xw.controller;

import com.xw.domain.ResponseResult;
import com.xw.domain.entity.LoginUser;
import com.xw.domain.entity.Menu;
import com.xw.domain.entity.User;
import com.xw.domain.vo.AdminUserInfoVo;
import com.xw.domain.vo.RoutersVo;
import com.xw.domain.vo.UserInfoVo;
import com.xw.enums.AppHttpCodeEnum;
import com.xw.exception.SystemException;
import com.xw.service.AdminLoginService;
import com.xw.service.MenuService;
import com.xw.service.RoleService;
import com.xw.utils.BeanCopyUtils;
import com.xw.utils.SecurityUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Security;
import java.util.List;

@RestController
public class AdminLoginController {

    @Resource
    private AdminLoginService adminLoginService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult adminLogin(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }

    @PostMapping("/user/logout")
    public ResponseResult logout() {
        return adminLoginService.logout();
    }

    /**
     * 用户的角色和权限信息
     * @return
     */
    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo() {
        // 获取当前用户的角色和权限信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        List<String> roleKeys = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        // 返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeys, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    /**
     * 返回用户能访问的菜单数据
     * @return
     */
    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        return ResponseResult.okResult(new RoutersVo(menus));
    }
}
