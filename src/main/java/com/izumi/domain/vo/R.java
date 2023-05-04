package com.izumi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    private int code;// 200 代表成功， 500 代表失败
    private String msg;// 返回提示信息
    private String status;// 要么成功，要么失败
    private Object data;// 返回数据
    public static final String SUCCESS = "success";// 成功
    public static final String FAIL = "fail";// 失败
    public static R ok() {
        return new R(200, "", SUCCESS, null);
    }
    public static R ok(String msg) {
        return new R(200, msg, SUCCESS, null);
    }
    public static R ok(Object data) {
        return new R(200, "", SUCCESS, data);
    }
    public static R ok(String msg, Object data) {
        return new R(200, msg, SUCCESS, data);
    }
    public static R error() {
        return new R(500, "", FAIL, null);
    }
    public static R error(String msg) {
        return new R(500, msg, FAIL, null);
    }
    public static R error(int code) {
        return new R(code, "", FAIL, null);
    }
    public static R error(int code, String msg) {
        return new R(code, msg, FAIL, null);
    }
}
