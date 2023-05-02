package com.izumi.web.controller;

import com.izumi.data.DepartmentData;
import com.izumi.domain.Department;
import com.izumi.query.QueryObject;
import com.izumi.service.IDepartmentService;
import com.izumi.util.JsonResult;
import com.izumi.util.PageResult;
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
        PageResult<Department> result = departmentService.query(queryObject);
        // 先解决JsonResult 的 Data 数据
        DepartmentData data = new DepartmentData(result.getCurrentPage(), result.getPageSize(), result.getData(), result.getTotalCount());


        return JsonResult.success(data);
    }
}
