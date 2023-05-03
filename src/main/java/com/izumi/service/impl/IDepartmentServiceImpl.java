package com.izumi.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.izumi.domain.Department;
import com.izumi.exception.BussinessExp;
import com.izumi.mapper.DepartmentMapper;
import com.izumi.query.QueryObject;
import com.izumi.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class IDepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    // 查询所有部门
    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }
    /**
     * 分页查询
     * @param qo
     * @return
     */
    @Override
    public PageInfo selectByPage(QueryObject qo) {
        // PageHelper进行分页操作
        // 告诉PageHelper当前页是多少、每页显示数据多少
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        // 调用分页方法
       List<Department> departments =  departmentMapper.queryForList(qo);
       return new PageInfo(departments);
    }

    /**
     * 通过id删除部门
     * @param id
     * @return
     */
    @Override
    public void deleteById(Long id) {
        // 断言
       Assert.state(departmentMapper.deleteById(id)>0,"删除失败");
    }

    @Override
    public void saveOrUpdate(Department department) {
        // 添加部门信息
        if (department.getId() == null) {
            Assert.state(departmentMapper.insert(department)>0,
                    "添加失败");
            return;
        }
        // 更新部门信息
        Assert.state( departmentMapper.updateById(department)>0,
                "更新失败");
    }

    // 获取单个部门
    @Override
    public Department selectById(Long id) {
        if(id == null) {
            throw new BussinessExp("非法参数");
        }
        Department department = departmentMapper.selectById(id);
        if(ObjectUtils.isEmpty(department)) {
            throw new BussinessExp("查询数据不存在");
        }
        return departmentMapper.selectById(id);
    }
}
