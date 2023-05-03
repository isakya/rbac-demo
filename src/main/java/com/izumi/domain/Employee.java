package com.izumi.domain;

import lombok.Data;

import java.util.List;

@Data
public class Employee {
    private Long id;// 员工id
    private String name; // 员工名字
    private String password;// 密码
    private String email;// 邮箱
    private int age;// 年龄
    private boolean admin; // 是否是管理员
    private Department department; // 部门
}
