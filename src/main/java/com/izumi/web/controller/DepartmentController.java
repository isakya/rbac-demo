package com.izumi.web.controller;

import com.github.pagehelper.PageInfo;
import com.izumi.data.ResultData;
import com.izumi.domain.Department;
import com.izumi.query.QueryObject;
import com.izumi.service.IDepartmentService;
import com.izumi.util.JsonResult;
import com.izumi.util.RequirePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;


    @GetMapping("/list")
    @RequirePermission({"部门列表","department:list"})
    public JsonResult list(QueryObject queryObject) {
        PageInfo<Department> result = departmentService.selectByPage(queryObject);
        ResultData data = new ResultData(result.getPageNum(),
                result.getPageSize(), result.getList(),
                Integer.parseInt(result.getTotal() + ""));
        return JsonResult.success(data);
    }

    @DeleteMapping("/delete/{id}")
    @RequirePermission({"部门删除","department:delete"})
    public JsonResult delete(@PathVariable Long id) {
        departmentService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping("/saveOrUpdate")
    @RequirePermission({"部门新增或编辑","department:saveOrUpdate"})
    public JsonResult saveOrUpdate(@RequestBody Department department) {
        departmentService.saveOrUpdate(department);
        return JsonResult.success();
    }

    // 查询单个部门
    @GetMapping("/info/{id}")
    @RequirePermission({"查询单个部门","department:info"})
    public JsonResult info(@PathVariable Long id) {
        Department department = departmentService.selectById(id);
        return JsonResult.success(department);
    }

    // 获取所有部门
    @GetMapping("/listAll")
    @RequirePermission({"查询所有部门","department:listAll"})
    public JsonResult listAll() {
        List<Department> departments = departmentService.selectAll();
        return JsonResult.success(departments);
    }
}
