package com.xw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.deploy.util.SystemUtils;
import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddUserDto;
import com.xw.domain.entity.Role;
import com.xw.domain.entity.User;
import com.xw.domain.vo.PageVo;
import com.xw.domain.vo.UserInfoVo;
import com.xw.enums.AppHttpCodeEnum;
import com.xw.exception.SystemException;
import com.xw.mapper.UserMapper;
import com.xw.service.RoleService;
import com.xw.service.UserService;
import com.xw.utils.BeanCopyUtils;
import com.xw.utils.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sun.plugin2.util.SystemUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 用户表(User)表服务实现类
 *
 * @author xw
 * @since 2023-03-17 15:41:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RoleService roleService;

    @Override
    public ResponseResult userInfo() {
        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        Long userId = SecurityUtils.getUserId();
        if (!userId.equals(user.getId())) {
            // 只能修改当前登录用户的信息
            return ResponseResult.okResult();
        }
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        // 判断数据非空
        if (!StringUtils.hasText(user.getUserName()) ||
            !StringUtils.hasText(user.getPassword()) ||
            !StringUtils.hasText(user.getEmail()) ||
            !StringUtils.hasText(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.USER_INFO_NOT_NULL);
        }
        // 判断用户名和昵称是否已存在
        if (userNameOrNickNameExist(user.getUserName(), user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        // 密码加密
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        // 保存
        save(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult pageList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (userName != null && !userName.equals("")) {
            wrapper.like(User::getUserName, userName);
        }
        if (phonenumber != null && !phonenumber.equals("")) {
            wrapper.eq(User::getPhonenumber, phonenumber);
        }
        if (status != null && !status.equals("")) {
            wrapper.eq(User::getStatus, status);
        }
        if (pageNum == null || pageNum < 0) {
            pageNum = SystemConstants.DEFAULT_PAGE_NUM;
        }
        if (pageSize == null || pageSize < 0) {
            pageSize = SystemConstants.DEFAULT_PAGE_SIZE;
        }
        Page<User> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    @Transactional
    public ResponseResult addUser(AddUserDto userDto) {
        // 验证注册信息
        if (!StringUtils.hasText(userDto.getUserName())) {
            throw new SystemException(500, "必须填写用户名");
        }
        if (!StringUtils.hasText(userDto.getPassword())) {
            throw new SystemException(500, "必须填密码");
        }
        if (userNameOrNickNameExist(userDto.getUserName(), userDto.getNickName())) {
            throw new SystemException(500, "用户名或昵称已存在");
        }
        if (count(new LambdaQueryWrapper<User>()
                .eq(User::getPhonenumber, userDto.getPhonenumber())) > 0) {
            throw new SystemException(500, "手机号已存在");
        }
        if (count(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, userDto.getEmail())) > 0) {
            throw new SystemException(500, "邮箱已存在");
        }
        // 保存用户信息
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        save(user);
        // 保存用户角色信息
        UserMapper userMapper = getBaseMapper();
        userMapper.saveBatchUserRole(user.getId(), userDto.getRoleIds());
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteUser(Long id) {
        if (id == null || id < 0) {
            throw new SystemException(500, "用户id为空");
        }
        if (SecurityUtils.getUserId().equals(id)) {
            throw new SystemException(500, "不能删除当前操作的用户");
        }
        removeById(id);
        UserMapper userMapper = getBaseMapper();
        userMapper.deleteUserRoleById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUser(Long id) {
        if (id == null || id < 0) {
            throw new SystemException(500, "用户id为空");
        }
        // 用户信息
        User user = getById(id);
        // 所有角色信息列表
        List<Role> roles = (List<Role>) roleService.listAllRole().getData();
        // 用户关联的角色id
        UserMapper userMapper = getBaseMapper();
        List<String> roleIds = userMapper.getUserRoles(id);
        // 封装返回
        HashMap<String, Object> res = new HashMap<>();
        res.put("user", user);
        res.put("roles", roles);
        res.put("roleIds", roleIds);
        return ResponseResult.okResult(res);
    }

    @Override
    public ResponseResult updateUser(AddUserDto userDto) {
        // 保存用户信息
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        updateById(user);

        UserMapper userMapper = getBaseMapper();
        // 删除旧的角色信息
        userMapper.deleteUserRoleById(user.getId());
        // 保存用户角色信息
        userMapper.saveBatchUserRole(user.getId(), userDto.getRoleIds());
        return ResponseResult.okResult();
    }

    private boolean userNameOrNickNameExist(String username, String nickName) {
        int count = count(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, username)
                .or()
                .eq(User::getNickName, nickName));
        return count > 0;
    }
}

