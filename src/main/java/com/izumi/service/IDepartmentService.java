package com.izumi.service;



import com.izumi.domain.Department;
import com.izumi.query.QueryObject;
import com.izumi.util.PageResult;

import java.util.List;

public interface IDepartmentService {
    List<Department> selectAll();
    void deleteById(Long id);
    void saveOrUpdate(Department department);
    Department selectById(Long id);
    PageResult<Department> selectByPage(QueryObject qo);
    PageResult<Department> query(QueryObject queryObject);
}
