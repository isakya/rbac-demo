package com.izumi.constant;

public class RedisConstant {
    // uuid前缀
    public static final String LOGIN_VERIFY_CODE = "LOGIN_VERIFY_CODE:";

    // 过期时间
    public static final Long VERIFY_CODE_EXPIRE_TIME = 60*30L;

    // 当前登陆用户信息的前缀
    public static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";

    // 过期时间
    public static final Long LOGIN_INFO_EXPIRE_TIME = 60*30L;

    // 当前用户所拥有的权限表达式集合
    public static final String LOGIN_INFO_EXPRESSIONS = "LOGIN_USER_INFO";
}
