package com.izumi.service;


import com.github.pagehelper.PageInfo;
import com.izumi.domain.Role;
import com.izumi.query.QueryObject;
import com.izumi.vo.RoleVo;
import com.izumi.web.controller.PageResult;

import java.util.List;

public interface IRoleService {
    /**
     * @param id
     * 删除角色
     */
    void deleteById(Long id);

    void saveOrUpdate(RoleVo roleVo);

    Role selectById(Long id);

    List<Role> listAll();

    // 分页查询的方法
    PageResult<Role> selectByPage(QueryObject qo);

    List<Role> queryRolesByEmployeeId(Long employeeId);
}
