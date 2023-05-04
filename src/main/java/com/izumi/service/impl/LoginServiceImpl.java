package com.izumi.service.impl;

import com.izumi.constant.RedisConstant;
import com.izumi.domain.Employee;
import com.izumi.exception.BussinessExp;
import com.izumi.service.IEmployeeService;
import com.izumi.service.ILoginService;
import com.izumi.util.RedisUtils;
import com.izumi.util.VerifyCodeUtil;
import com.izumi.vo.LoginInfoVo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IEmployeeService employeeService;

    @Override
    public Map<String, String> code() {
        // 用工具类生成验证码
        Map<String, String> map = VerifyCodeUtil.generateVerifyCode();
        // 把数据保存到redis当中
        // 数据类型：String类型，key:前缀:uuid value:code
        String uuid = map.get("uuid");
        String code = map.get("code");
        System.out.println(code);
        redisUtils.set(RedisConstant.LOGIN_VERIFY_CODE + uuid, code, RedisConstant.VERIFY_CODE_EXPIRE_TIME);

        // 返回map
        map.remove("code");
        return map;
    }

    @Override
    public Employee login(LoginInfoVo loginInfoVo) {
        // 1 判断vo不为空
        if(loginInfoVo == null) {
            throw new BussinessExp("非法操作");
        }
        // 2 判断账号密码不为空
        if(StringUtils.isEmpty(loginInfoVo.getUsername())
                || StringUtils.isEmpty(loginInfoVo.getPassword())) {
            throw  new BussinessExp("账号和密码不能为空");
        }
        // 3 判断验证码不为空
        if(StringUtils.isEmpty(loginInfoVo.getCode())) {
            throw  new BussinessExp("验证码不能为空");
        }
        // 4 上redis中去查询数据 验证码
        String code = redisUtils.get(RedisConstant.LOGIN_VERIFY_CODE + loginInfoVo.getUuid());
        if(StringUtils.isEmpty(code)) {
            throw  new BussinessExp("验证码错误");
        }
        // 5 对比传过来的验证码是否一致
        boolean flag = VerifyCodeUtil.verification(code, loginInfoVo.getCode(), true);
        if(!flag) {
            throw  new BussinessExp("验证码错误");
        }
        // 6 根据账号和密码去数据库查询数据
        Employee employee = employeeService.login(loginInfoVo.getUsername(), loginInfoVo.getPassword());
        // 7 如果不为空，把用户信息保存到redis中
        // 8 把当前用户所拥有的权限表达式集合放到redis当中
        return employee;
    }
}
