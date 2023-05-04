package com.izumi.constant;

public class RedisConstant {
    // uuid前缀
    public static final String LOGIN_VERIFY_CODE = "LOGIN_VERIFY_CODE:";

    // 过期时间
    public static final Long VERIFY_CODE_EXPIRE_TIME = 60*30L;
}
