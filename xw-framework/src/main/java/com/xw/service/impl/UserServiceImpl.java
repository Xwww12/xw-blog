package com.xw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.deploy.util.SystemUtils;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.User;
import com.xw.domain.vo.UserInfoVo;
import com.xw.enums.AppHttpCodeEnum;
import com.xw.exception.SystemException;
import com.xw.mapper.UserMapper;
import com.xw.service.UserService;
import com.xw.utils.BeanCopyUtils;
import com.xw.utils.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.plugin2.util.SystemUtil;

import javax.annotation.Resource;

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

    private boolean userNameOrNickNameExist(String username, String nickName) {
        int count = count(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, username)
                .or()
                .eq(User::getNickName, nickName));
        return count > 0;
    }
}

