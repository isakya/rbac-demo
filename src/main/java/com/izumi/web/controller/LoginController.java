package com.izumi.web.controller;

import com.izumi.service.ILoginService;
import com.izumi.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
