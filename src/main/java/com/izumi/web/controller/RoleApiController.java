package com.izumi.web.controller;

import com.izumi.domain.vo.R;
import com.izumi.query.QueryObject;
import com.izumi.service.IRoleService;
import com.izumi.util.RequirePermission;
import com.izumi.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/role")
@CrossOrigin(allowCredentials="true")
public class RoleApiController {
    @Autowired
    private IRoleService roleService;

    @GetMapping("/listAll")
    @ResponseBody
    @RequirePermission({"角色列表","role:listAll"})
    public R listAll() {
        return R.ok(roleService.listAll());
    }

    @GetMapping("/list")
    @ResponseBody
    @RequirePermission({"角色分页列表","role:list"})
    public R list(QueryObject qo) {
        return R.ok(roleService.selectByPage(qo));
    }


    @DeleteMapping("/delete/{id}")
    @ResponseBody
    @RequirePermission({"角色删除","role:delete"})
    public R delete(@PathVariable Long id) {
        roleService.deleteById(id);
        return R.ok("删除成功");
    }

    @GetMapping("/info/{id}")
    @ResponseBody
    @RequirePermission({"角色信息","role:info"})
    public R info(@PathVariable Long id) {
        return R.ok(roleService.selectById(id));
    }

    @GetMapping("/query/{employeeId}")
    @ResponseBody
    @RequirePermission({"角色信息","role:query"})
    public R queryRoles(@PathVariable Long employeeId) {
        return R.ok(roleService.queryRolesByEmployeeId(employeeId));
    }

    @PostMapping("/saveOrUpdate")
    @ResponseBody
    @RequirePermission({"角色保存或更新","role:saveOrUpdate"})
    public R saveOrUpdate(@RequestBody RoleVo roleVo) {
        roleService.saveOrUpdate(roleVo);
        return R.ok("保存或者更新成功");
    }


}
