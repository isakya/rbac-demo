package com.izumi.web.controller;

import com.github.pagehelper.PageInfo;
import com.izumi.data.ResultData;
import com.izumi.domain.Employee;
import com.izumi.query.EmployeeQueryObject;
import com.izumi.service.IEmployeeService;
import com.izumi.util.JsonResult;
import com.izumi.util.RequirePermission;
import com.izumi.vo.AdminStateVo;
import com.izumi.vo.EmployeeRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;


    @GetMapping("/list")
    @RequirePermission({"员工列表","employee:list"})
    public JsonResult list(EmployeeQueryObject queryObject) {
        PageInfo<Employee> result = employeeService.selectByPage(queryObject);
        ResultData data = new ResultData(result.getPageNum(),
                result.getPageSize(), result.getList(),
                Integer.parseInt(result.getTotal() + ""));
        return JsonResult.success(data);
    }

    @DeleteMapping("/delete/{id}")
    @RequirePermission({"删除员工","employee:delete"})
    public JsonResult delete(@PathVariable Long id) {
        employeeService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping("/saveOrUpdate")
    @RequirePermission({"新增或编辑员工","employee:saveOrUpdate"})
    public JsonResult saveOrUpdate(@RequestBody EmployeeRoleVo employeeRoleVo) {
        employeeService.saveOrUpdate(employeeRoleVo);
        return JsonResult.success();
    }

    @GetMapping("/info/{id}")
    @RequirePermission({"查询单个员工","employee:info"})
    public JsonResult info(@PathVariable Long id) {
        Employee employee = employeeService.selectById(id);
        return JsonResult.success(employee);
    }

    @GetMapping("/listAll")
    @RequirePermission({"查询所有员工","employee:listAll"})
    public JsonResult listAll() {
        List<Employee> employees = employeeService.selectAll();
        return JsonResult.success(employees);
    }

    @PostMapping("/updateState")
    @RequirePermission({"更改管理员状态","employee:updateState"})
    public JsonResult updateState(@RequestBody AdminStateVo adminStateVo) {
        employeeService.updateState(adminStateVo);
        return JsonResult.success();
    }

}
