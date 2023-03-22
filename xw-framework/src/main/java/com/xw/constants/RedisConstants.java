package com.xw.constants;

public class RedisConstants {

    /**
     * 登录前缀
     */
    public static final String CACHE_LOGIN_PREFIX = "blog:login:";

    /**
     * 超时时间，单位秒
     */
    public static final Integer CACHE_TTL = 24 * 60 * 60;

    /**
     * 浏览量前缀
     */
    public static final String VIEW_COUNT = "article:viewCount";

    /**
     * 后台登录前缀
     */
    public static final String CACHE_ADMIN_LOGIN_PREFIX = "admin:login:";
}
