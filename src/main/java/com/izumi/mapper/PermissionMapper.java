package com.izumi.mapper;

import com.izumi.domain.Permission;
import com.izumi.query.QueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;
@Mapper
public interface PermissionMapper {
    List<Permission> selectAll();

    List<Permission> selectForList(QueryObject qo);

    void batchInsert(@Param("permissions") Set<Permission> permissions);

    List<Permission> selectByRoleId(Long roleId);

    //根据员工ID查询权限表达式
    List<String> selectExpressionByEmpId(Long empId);
}
