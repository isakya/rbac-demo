package com.izumi.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.izumi.domain.Permission;
import com.izumi.mapper.PermissionMapper;
import com.izumi.query.QueryObject;
import com.izumi.service.IPermissionService;
import com.izumi.util.RequirePermission;
import com.izumi.web.controller.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    // 拿到spring容器对象
    @Autowired
    private ApplicationContext ctx;
    @Override
    public List<Permission> listAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public PageResult<Permission> query(QueryObject qo) {
        // 下面执行查询是要分页的
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        PageInfo pageInfo=new PageInfo<>(permissionMapper.selectForList(qo));
        PageResult<Permission> pageResult = new PageResult<>();
        BeanUtils.copyProperties(pageInfo,pageResult);
        return pageResult;
    }

    @Override
    public List<Permission> queryByRoleId(Long id) {
        Assert.notNull(id,"非法参数");
        return permissionMapper.selectByRoleId(id);
    }

    // 生成权限列表并写入数据库
    @Override
    public void load() {
        // 0 从数据库查询所有权限
        List<Permission> existPermissions = permissionMapper.selectAll();
        // 1 获取容器带有 @Controller 注解的控制器对象
        Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
        // 2 获取所有控制器对象
        Collection<Object> values = beansWithAnnotation.values();
        Set<Permission> permissionSet=values.stream().flatMap(controller->{
            // 获取所有控制器的处理方法
            Method[] methods = controller.getClass().getDeclaredMethods();
            return Arrays.stream(methods).filter(method ->
                    method.getAnnotation(RequirePermission.class) != null)
                    .map(method -> {
                        RequirePermission annotation = method.getAnnotation(RequirePermission.class);
                        Permission permission = new Permission();
                        permission.setName(annotation.value()[0]);
                        permission.setExpression(annotation.value()[1]);
                        return permission;
                    }).filter(permission -> !existPermissions.contains(permission));
        }).collect(Collectors.toSet());
        if(permissionSet.size()>0){
            permissionMapper.batchInsert(permissionSet);
        }
    }

    private void backupLoad3() {
        // 0 从数据库查询所有权限
        List<Permission> existPermissions = permissionMapper.selectAll();
        // 1 获取容器带有 @Controller 注解的控制器对象
        Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
        // 2 获取所有控制器对象
        Collection<Object> values = beansWithAnnotation.values();
        Set<Permission> permissionSet=values.stream().flatMap(controller->{
            // 获取所有控制器的处理方法
            Method[] methods = controller.getClass().getDeclaredMethods();
            return Arrays.stream(methods).filter(method ->
                    method.getAnnotation(RequirePermission.class) != null)
                    .map(method -> {
                        RequirePermission annotation = method.getAnnotation(RequirePermission.class);
                        Permission permission = new Permission();
                        permission.setName(annotation.value()[0]);
                        permission.setExpression(annotation.value()[1]);
                        return permission;
                    }).filter(permission -> !existPermissions.contains(permission));
        }).collect(Collectors.toSet());
        if(permissionSet.size()>0){
            permissionMapper.batchInsert(permissionSet);
        }
    }

    private void backupLoad2() {
        // 0 从数据库查询所有权限
        List<Permission> existPermissions = permissionMapper.selectAll();
        // 1 获取容器带有 @Controller 注解的控制器对象
        Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
        // 2 获取所有控制器对象
        Collection<Object> values = beansWithAnnotation.values();
        Set<Permission> permissionSet=new LinkedHashSet<>();
        values.stream().forEach(controller->{
            // 获取所有控制器的处理方法
            Method[] methods = controller.getClass().getDeclaredMethods();
            Set<Permission> permissions = Arrays.stream(methods).filter(method ->
                    method.getAnnotation(RequirePermission.class) != null)
                    .map(method -> {
                        RequirePermission annotation = method.getAnnotation(RequirePermission.class);
                        Permission permission = new Permission();
                        permission.setName(annotation.value()[0]);
                        permission.setExpression(annotation.value()[1]);
                        return permission;
                    }).filter(permission -> !existPermissions.contains(permission))
                    .collect(Collectors.toSet());
            permissionSet.addAll(permissions);
        });
        if(permissionSet.size()>0){
            permissionMapper.batchInsert(permissionSet);
        }
    }

    private void backupLoad() {
        // 0 从数据库查询所有权限
        List<Permission> existPermissions = permissionMapper.selectAll();
        // 1 获取容器带有 @Controller 注解的控制器对象
        Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
        // 2 获取所有控制器对象
        Collection<Object> values = beansWithAnnotation.values();
        Set<Permission> permissions = new LinkedHashSet<>();
        for (Object controller : values) {
            // 获取所有控制器的处理方法
            Method[] methods = controller.getClass().getDeclaredMethods();
            for (Method method : methods) {
                // 获取处理方法上自定义权限注解
                RequirePermission anno = method.getAnnotation(RequirePermission.class);
                // 并不是所有处理方法都有权限注解
                if(anno!=null){
                    Permission permission = new Permission();
                    permission.setName(anno.value()[0]);
                    permission.setExpression(anno.value()[1]);
                    if(!existPermissions.contains(permission)){
                        permissions.add(permission);
                    }
                }
            }
        }
        // 3 保存所有权限
        if(permissions.size()>0){
            permissionMapper.batchInsert(permissions);
        }
    }
}
