package com.xw.service;

import com.xw.domain.ResponseResult;
import com.xw.domain.entity.User;

public interface AdminLoginService {
    ResponseResult login(User user);

    ResponseResult logout();

}
