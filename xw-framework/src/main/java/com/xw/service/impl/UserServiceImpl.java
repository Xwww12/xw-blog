package com.xw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.User;
import com.xw.domain.vo.UserInfoVo;
import com.xw.mapper.UserMapper;
import com.xw.service.UserService;
import com.xw.utils.BeanCopyUtils;
import com.xw.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author xw
 * @since 2023-03-17 15:41:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }
}

