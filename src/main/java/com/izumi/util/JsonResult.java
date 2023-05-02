package com.izumi.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonResult {
    private Integer code;
    private String msg;
    private String status;
    private Object data;


    public JsonResult(Integer code, String msg, String status, Object data) {
        this.code = code;
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public static JsonResult success() {
        return new JsonResult(200, "操作成功", "success", null);
    }

    public static JsonResult success(Object data) {
        return new JsonResult(200, "操作成功", "success", data);
    }

    public static JsonResult fail() {
        return new JsonResult(500, "操作失败", "fail", null);
    }

}
