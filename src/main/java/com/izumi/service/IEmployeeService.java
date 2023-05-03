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
    void updateStateById(AdminStateVo adminStateVo);
    Employee selectById(Long id);
}
