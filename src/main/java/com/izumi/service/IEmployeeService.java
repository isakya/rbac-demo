package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.query.EmployeeQueryObject;
import cn.wolfcode.rbac.domain.vo.AdminStateVo;
import cn.wolfcode.rbac.domain.vo.EmployeeRoleVo;
import cn.wolfcode.rbac.domain.vo.PageResult;

import java.util.List;

public interface IEmployeeService {
    List<Employee> selectAll();
    PageResult<Employee> selectByPage(EmployeeQueryObject qo);
    void deleteById(Long id);
    void saveOrUpadate(EmployeeRoleVo employeeRoleVo);
    void updateStateById(AdminStateVo adminStateVo);
    Employee selectById(Long id);
}
