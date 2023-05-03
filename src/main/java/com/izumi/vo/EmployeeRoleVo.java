package cn.wolfcode.rbac.domain.vo;

import cn.wolfcode.rbac.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRoleVo {
    private Employee employee;
    private Long[] roleIds;
}
