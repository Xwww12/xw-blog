package com.xw.service.impl;

import com.xw.constants.RedisConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.LoginUser;
import com.xw.domain.entity.User;
import com.xw.domain.vo.BlogUserLoginVo;
import com.xw.domain.vo.UserInfoVo;
import com.xw.service.BlogLoginService;
import com.xw.utils.BeanCopyUtils;
import com.xw.utils.JwtUtil;
import com.xw.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        // 包装成用于用户身份认证的token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        // 调用Manager的身份认证方法实现认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码异常");
        }
        // 通过用户id生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = String.valueOf(loginUser.getUser().getId());
        String jwt = JwtUtil.createJWT(id);
        // 存入redis
        redisCache.setCacheObject(RedisConstants.CACHE_LOGIN_PREFIX + id, loginUser, RedisConstants.CACHE_TTL, TimeUnit.SECONDS);

        // 返回vo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();

        redisCache.deleteObject(RedisConstants.CACHE_LOGIN_PREFIX + userId);
        return ResponseResult.okResult();
    }
}
