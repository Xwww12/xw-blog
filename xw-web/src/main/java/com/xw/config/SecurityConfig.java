package com.xw.config;

import com.xw.filter.JwtAuthenticationTokenFilter;
import com.xw.handler.security.AccessDeniedHandlerImpl;
import com.xw.handler.security.AuthenticationEntryPointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭csrf防范
                .csrf().disable()
                // 不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 允许匿名访问的接口
                .antMatchers("/login").permitAll()
                // 需要认证接口
                .antMatchers(
                        "/logout").authenticated()
                // 接口都无需鉴权
                .anyRequest().permitAll();
        //配置异常处理器
        http.exceptionHandling()
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler);

        http.logout().disable();
        // 添加对jwt的过滤器
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 允许跨域
        http.cors();
    }

    // 自定义认证异常处理器
    @Resource
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    // 自定义鉴权异常处理器
    @Resource
    private AccessDeniedHandlerImpl accessDeniedHandler;

    @Resource
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    // 密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 注入身份认证器
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
