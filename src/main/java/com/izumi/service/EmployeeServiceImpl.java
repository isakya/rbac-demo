package com.izumi.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.izumi.domain.Employee;
import com.izumi.exception.BussinessExp;
import com.izumi.mapper.EmployeeMapper;
import com.izumi.query.EmployeeQueryObject;
import com.izumi.vo.AdminStateVo;
import com.izumi.vo.EmployeeRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }
    @Override
    public PageInfo<Employee> selectByPage(EmployeeQueryObject qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new PageInfo(employeeMapper.queryForList(qo));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Assert.notNull(id,"非法參數");
        Assert.state(employeeMapper.deleteById(id)>0,"删除失败");
        employeeMapper.deleteRelation(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(EmployeeRoleVo employeeRoleVo) {
        Assert.notNull(employeeRoleVo,"非法參數");
        Assert.notNull(employeeRoleVo.getEmployee(),"非法參數");
        if(employeeRoleVo.getEmployee().getId()==null){
            // 添加员工
            save(employeeRoleVo.getEmployee(),employeeRoleVo.getRoleIds());
        }else{
            // 更新员工信息
            update(employeeRoleVo.getEmployee(),employeeRoleVo.getRoleIds());
        }
    }

    @Transactional
    public void save(Employee employee, Long[] roleIds) {
        // 保存员工信息
        employeeMapper.insert(employee);
        // 保存员工角色表 employee_role
        saveRelation(employee, roleIds);

    }

    @Transactional
    public void update(Employee employee, Long[] roleIds) {
        //更新员工信息
        employeeMapper.updateById(employee);
        //更新员工角色表(先删后添加)
        employeeMapper.deleteRelation(employee.getId());
        saveRelation(employee, roleIds);
    }

    @Override
    public void updateStateById(AdminStateVo adminStateVo) {
        Assert.notNull(adminStateVo,"非法參數");
        Assert.state(  employeeMapper.updateStateById(adminStateVo.getId(),adminStateVo.isAdmin())>0,
                "更新状态失败");
    }


    private void saveRelation(Employee employee, Long[] roleIds) {
        if(roleIds != null && roleIds.length>0){
            for (Long roleId : roleIds) {
                employeeMapper.insertRelation(employee.getId(),roleId);
            }
        }
    }

    @Override
    public Employee selectById(Long id) {
        if(id == null) {
            throw new BussinessExp("参数不能为空");
        }
        Employee employee = employeeMapper.selectById(id);
        if(employee == null) {
            throw new BussinessExp("非法操作");
        }
        return employeeMapper.selectById(id);
    }
}
