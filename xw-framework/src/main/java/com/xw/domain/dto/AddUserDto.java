package com.xw.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddUserDto {
    // 用户id
    private Long id;
    //用户名
    private String userName;
    //昵称
    private String nickName;
    //密码
    private String password;
    //邮箱
    private String email;
    //手机号
    private String phonenumber;
    //用户性别（0男，1女，2未知）
    private String sex;
    //头像
    private String avatar;

    List<String> roleIds;
}
