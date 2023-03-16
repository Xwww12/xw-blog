package com.xw.filter;

import com.alibaba.fastjson.JSON;
import com.xw.constants.RedisConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.LoginUser;
import com.xw.enums.AppHttpCodeEnum;
import com.xw.utils.JwtUtil;
import com.xw.utils.RedisCache;
import com.xw.utils.WebUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 认证过滤器
 * 将用户信息保存到SecurityContextHolder
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userId = null;
        try {
            userId = getUserIdFromRequest(request, response, filterChain);
        } catch (Exception e) {
            // token已过期或非法
            log.warn(e.getMessage());
            return;
        }
        if (userId != null) {
            // 查询redis中是否有对应用户信息
            LoginUser loginUser = redisCache.getCacheObject(RedisConstants.CACHE_LOGIN_PREFIX + userId);
            if (Objects.isNull(loginUser)) {
                // redis中过期
                ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
                WebUtils.renderString(response, JSON.toJSONString(result));
                return;
            }
            // 存入SecurityContextHolder
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getUserIdFromRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // 获取请求头中的token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            return null;
        }
        // 解析出用户id
        String userId = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            throw new RuntimeException("token非法或已过期");
        }
        return userId;
    }
}
