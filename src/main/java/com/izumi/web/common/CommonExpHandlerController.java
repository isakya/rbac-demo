package com.izumi.web.common;

import com.izumi.exception.BussinessExp;
import com.izumi.util.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExpHandlerController {
    // 自定义异常，提示自定义信息
    @ExceptionHandler(BussinessExp.class)
    @ResponseBody
    public JsonResult handler(BussinessExp e) {
        e.printStackTrace();
        return JsonResult.fail(e.getMessage());
    }

    // 如果是系统异常，提示操作失败即可
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult handler(RuntimeException e) {
        e.printStackTrace();
        return JsonResult.fail();
    }
}
