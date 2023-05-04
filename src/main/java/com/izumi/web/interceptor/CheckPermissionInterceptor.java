package com.izumi.web.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.izumi.domain.Employee;
import com.izumi.domain.vo.R;
import com.izumi.util.RedisUtils;
import com.izumi.util.RequirePermission;
import com.izumi.web.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 权限认证拦截器
 *      拦截所有请求对请求验证，验证登录的用户是否具有执行操作的权限
 *          获取登录员工
 *          判断是有为超级管理员
 *              如果是，直接放行
 *
 *              如果不是，判断是否具有执行操作的权限
 *                  如果有执行的权限，放行
 *                  如果没有，拦截请求，跳转到没有权限的页面中
 *
 */
@Component
public class CheckPermissionInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", ((HttpServletRequest) request).getHeader("Access-Control-Request-Headers"));
        response.setContentType("application/json;charset=utf-8");
        if(!(handler instanceof HandlerMethod)){ // 放行跨域的二次校验
            return true;
        }
        String userId = request.getHeader(Constants.USER_ID);
        Assert.notNull(userId,"非法操作");
       //1.获取登录的员工
        ObjectMapper mapper=new ObjectMapper();
        Employee loginEmployee =mapper.readValue(
                redisUtils.get(Constants.LOGIN_EMPLOYEE+":"+userId),Employee.class);
        if(loginEmployee==null){
            return false;
        }
        //2.判断是否为超级管理员
        if(loginEmployee.isAdmin()){
            //是超级管理员
            return true;
        }
        //不是超级管理员
        //判断是否具有执行当前操作的权限
        //HandlerMethod：处理请求方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //获取到当前执行的方法
        Method method = handlerMethod.getMethod();
        // 获取到当前要执行方法的权限注解
        RequirePermission annotation = method.getAnnotation(RequirePermission.class);
        //获取当前的权限数组
        String[] value = annotation.value();
        //获取权限的名称、权限表达式
        String name = value[0];
        String expression = value[1];
        //获取登录员工具有的权限
        List<String> expressions =mapper.readValue(
                redisUtils.get(Constants.EXPRESSION+":"+userId),List.class);
        if(expressions.contains(expression)){
            //有权限
            return true;
        }
        //无权限
        //跳转到没有权限的页面
        // 403
        response.getWriter().write(mapper.writeValueAsString(
                R.error(HttpStatus.FORBIDDEN.value(),"没有权限访问")));
        return false;
    }
}
