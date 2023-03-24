package com.xw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddUserDto;
import com.xw.domain.entity.User;

/**
 * 用户表(User)表服务接口
 *
 * @author xw
 * @since 2023-03-17 15:41:29
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult pageList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status);

    ResponseResult addUser(AddUserDto userDto);

    ResponseResult deleteUser(Long id);

    ResponseResult getUser(Long id);

    ResponseResult updateUser(AddUserDto user);
}

