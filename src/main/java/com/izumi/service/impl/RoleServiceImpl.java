package com.izumi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.izumi.domain.Role;
import com.izumi.mapper.RoleMapper;
import com.izumi.query.QueryObject;
import com.izumi.service.IRoleService;
import com.izumi.vo.RoleVo;
import com.izumi.web.controller.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional
    public void deleteById(Long id) {
        Assert.notNull(id,"非法参数");
        Assert.state(roleMapper.deleteByPrimaryKey(id)>0,"删除失败");
        // 删除角色关联权限信息
        roleMapper.deleteRelation(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(RoleVo roleVo) {
        // 参数校验
        Assert.notNull(roleVo,"非法参数");
        Assert.notNull(roleVo.getRole(),"非法参数");
        if(roleVo.getRole().getId()==null){
            //增加角色信息
            save(roleVo.getRole(),roleVo.getPermissionIds());
        }else{
            //更新角色信息
            update(roleVo.getRole(),roleVo.getPermissionIds());
        }
    }

    @Transactional
    public void save(Role role, Long[] permissionIds) {
        // 保存---角色表
        roleMapper.insert(role);
        // 保存---角色和权限表
        insertRelation(role, permissionIds);
    }

    private void insertRelation(Role role, Long[] permissionIds) {
        if(permissionIds!=null && permissionIds.length>0){
            roleMapper.insertBatchRelation(role.getId(), permissionIds);
        }
    }

    @Transactional
    public void update(Role role, Long[] permissionIds) {
        // 更新角色表
        roleMapper.updateByPrimaryKey(role);
        // 更新角色权限表
            // 先删除，再批添加
        roleMapper.deleteRelation(role.getId());
        insertRelation(role, permissionIds);
    }

    @Override
    public Role selectById(Long id) {
        // 参数校验
        Assert.notNull(id,"非法参数");
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.selectAll();
    }

    @Override
    public PageResult<Role> selectByPage(QueryObject qo) {
        PageHelper.startPage(qo.getPageNum(),qo.getPageSize());
        PageInfo<Role> pageInfo=new PageInfo<>(roleMapper.selectForList());
        PageResult<Role> pageResult=new PageResult<>();
        BeanUtils.copyProperties(pageInfo,pageResult);
        return pageResult;
    }

    @Override
    public List<Role> queryRolesByEmployeeId(Long employeeId) {
        // 参数校验
        Assert.notNull(employeeId,"非法参数");
        return roleMapper.selectRolesByEmployee(employeeId);
    }
}
