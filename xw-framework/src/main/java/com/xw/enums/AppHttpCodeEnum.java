package com.xw.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名或昵称已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    CONTENT_NOT_NULL(506, "评论内容不能为空"),
    FILE_TYPE_ERROR(507, "文件格式不支持"),
    FILE_EMPTY(508, "文件不能为空"),
    USER_INFO_NOT_NULL(509, "用户信息不能为空"),
    NAME_EXIST(510, "标签名为空或已存在"),
    TAG_NOT_EXIST(511, "标签不存在"),
    ARTICLE_NOT_EXIST(512, "文章不存在"),
    COMPONENT_IS_EXIST(512, "路由地址已存在");

    int code;
    String msg;

    AppHttpCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
