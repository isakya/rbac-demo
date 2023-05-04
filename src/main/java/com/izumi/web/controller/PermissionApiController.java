package com.izumi.web.controller;

import com.izumi.domain.vo.R;
import com.izumi.query.QueryObject;
import com.izumi.service.IPermissionService;
import com.izumi.util.RequirePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/permission")
@CrossOrigin(allowCredentials="true")
public class PermissionApiController {
    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/listAll")
    @ResponseBody
    @RequirePermission({"权限列表","permission:listAll"})
    public R listAll() {
        return R.ok(permissionService.listAll());
    }

    @GetMapping("/list")
    @ResponseBody
    @RequirePermission({"权限分页列表","permission:list"})
    public R list(QueryObject qo) {
        return R.ok(permissionService.query(qo));
    }


    /**
     * 加载权限
     * @return
     */
    @PostMapping("/load")
    @ResponseBody
    @RequirePermission({"加载权限","permission:load"})
    public R load() {
        permissionService.load();
        return R.ok("加载权限信息成功");
    }

    // 根据角色id查询角色拥有的权限
    @GetMapping("/queryPermission/{roleId}")
    @ResponseBody
    @RequirePermission({"查询权限","department:queryPermission"})
    public R queryPermission(@PathVariable long roleId) {
        return R.ok(permissionService.queryByRoleId(roleId));
    }

}
