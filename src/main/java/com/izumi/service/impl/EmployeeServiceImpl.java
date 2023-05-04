package com.izumi.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.izumi.domain.Employee;
import com.izumi.exception.BussinessExp;
import com.izumi.mapper.EmployeeMapper;
import com.izumi.query.EmployeeQueryObject;
import com.izumi.service.IEmployeeService;
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

    // 员工删除
    @Override
    @Transactional
    public void deleteById(Long id) {
        if(id == null) {
            throw new BussinessExp("非法操作");
        }
        if(employeeMapper.deleteById(id)==0) {
            throw new BussinessExp("删除失败");
        }
        employeeMapper.deleteRelation(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(EmployeeRoleVo employeeRoleVo) {
        if(employeeRoleVo == null) {
            throw new BussinessExp("非法操作");
        }
        if(employeeRoleVo.getEmployee() == null) {
            throw new BussinessExp("非法参数");
        }
        if(employeeRoleVo.getEmployee().getId() == null){
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

    // 员工信息编辑
    @Transactional
    public void update(Employee employee, Long[] roleIds) {
        //更新员工信息
        employeeMapper.updateById(employee);
        //更新员工角色表(先删后添加)
        employeeMapper.deleteRelation(employee.getId());
        saveRelation(employee, roleIds);
    }


    // 维护关系，往中间表去插入数据
    private void saveRelation(Employee employee, Long[] roleIds) {
        if(roleIds != null && roleIds.length>0){
            // for (Long roleId : roleIds) {
            //     employeeMapper.insertRelation(employee.getId(),roleId);
            // }
            // 批量插入
            employeeMapper.insertBatchRelation(employee.getId(), roleIds);
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

    // 设置管理员
    @Override
    public void updateState(AdminStateVo adminStateVo) {
        if(adminStateVo == null || adminStateVo.getId() == null) {
            throw new BussinessExp("非法操作");
        }
        // 更新数据库管理员状态
        int count = employeeMapper.updateState(adminStateVo.getId(), adminStateVo.isAdmin());
        if(count == 0) {
            throw new BussinessExp("更新失败");
        }
    }

    // 根据账号密码查询用户信息
    @Override
    public Employee login(String username, String password) {
        return employeeMapper.login(username, password);
    }
}
