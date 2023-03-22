package com.xw.service.impl;

import com.xw.constants.RedisConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.LoginUser;
import com.xw.domain.entity.User;
import com.xw.service.AdminLoginService;
import com.xw.utils.JwtUtil;
import com.xw.utils.RedisCache;
import com.xw.utils.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

@Service
public class SystemLoginServiceImpl implements AdminLoginService {
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 生成token存到redis中
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        redisCache.setCacheObject(RedisConstants.CACHE_ADMIN_LOGIN_PREFIX + userId, loginUser);
        // 封装返回
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        Long userId = SecurityUtils.getUserId();
        // 删除redis中的用户信息
        redisCache.deleteObject(RedisConstants.CACHE_ADMIN_LOGIN_PREFIX + userId);
        return ResponseResult.okResult();
    }
}
