package com.izumi.service.impl;

import com.izumi.constant.RedisConstant;
import com.izumi.service.ILoginService;
import com.izumi.util.RedisUtils;
import com.izumi.util.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Map<String, String> code() {
        // 用工具类生成验证码
        Map<String, String> map = VerifyCodeUtil.generateVerifyCode();
        // 把数据保存到redis当中
        // 数据类型：String类型，key:前缀:uuid value:code
        String uuid = map.get("uuid");
        String code = map.get("code");
        redisUtils.set(RedisConstant.LOGIN_VERIFY_CODE + uuid, code, RedisConstant.VERIFY_CODE_EXPIRE_TIME);

        // 返回map
        map.remove("code");
        return map;
    }
}
