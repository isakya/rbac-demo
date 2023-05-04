package com.izumi.web.controller;

import com.izumi.domain.Employee;
import com.izumi.service.ILoginService;
import com.izumi.util.JsonResult;
import com.izumi.vo.LoginInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @GetMapping("/code")
    public JsonResult code() {
        Map<String, String> map = loginService.code();
        return JsonResult.success(map);
    }

    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginInfoVo loginInfoVo) {
        Employee employee = loginService.login(loginInfoVo);
        return JsonResult.success(employee);
    }

    @GetMapping("/logout")
    public JsonResult logout(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        loginService.logout(userId);
        return JsonResult.success();
    }
}
