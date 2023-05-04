package com.izumi.service;

import com.izumi.domain.Employee;
import com.izumi.vo.LoginInfoVo;

import java.util.Map;

public interface ILoginService {
    Map<String, String> code();

    Employee login(LoginInfoVo loginInfoVo);

    void logout(String userId);
}
