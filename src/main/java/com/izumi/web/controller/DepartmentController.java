package com.izumi.web.controller;

import com.github.pagehelper.PageInfo;
import com.izumi.data.DepartmentData;
import com.izumi.domain.Department;
import com.izumi.query.QueryObject;
import com.izumi.service.IDepartmentService;
import com.izumi.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;


    @GetMapping("/list")
    public JsonResult list(QueryObject queryObject) {
        PageInfo<Department> result = departmentService.selectByPage(queryObject);
        DepartmentData data = new DepartmentData(result.getPageNum(),
                result.getPageSize(), result.getList(),
                Integer.parseInt(result.getTotal() + ""));
        return JsonResult.success(data);
    }
}
