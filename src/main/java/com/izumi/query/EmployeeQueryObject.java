package cn.wolfcode.rbac.domain.query;

import lombok.Data;

@Data
public class EmployeeQueryObject extends QueryObject {
    private String keyword;// 模糊查询参数，名称或者邮箱
    private Long deptId;// 部门id
}
