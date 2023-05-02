package com.izumi.service;



import com.github.pagehelper.PageInfo;
import com.izumi.domain.Department;
import com.izumi.query.QueryObject;
import com.izumi.web.controller.PageResult;

import java.util.List;

public interface IDepartmentService {
    List<Department> selectAll();
    void deleteById(Long id);
    void saveOrUpdate(Department department);
    Department selectById(Long id);
    PageInfo selectByPage(QueryObject qo);
}
