package com.izumi.service;



import com.github.pagehelper.PageInfo;
import com.izumi.domain.Employee;
import com.izumi.query.EmployeeQueryObject;
import com.izumi.vo.AdminStateVo;
import com.izumi.vo.EmployeeRoleVo;

import java.util.List;

public interface IEmployeeService {
    List<Employee> selectAll();
    PageInfo<Employee> selectByPage(EmployeeQueryObject qo);
    void deleteById(Long id);
    void saveOrUpdate(EmployeeRoleVo employeeRoleVo);
    Employee selectById(Long id);

    void updateState(AdminStateVo adminStateVo);

    Employee login(String username, String password);
}
