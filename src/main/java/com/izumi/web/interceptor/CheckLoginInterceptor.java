package com.izumi.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.izumi.constant.RedisConstant;
import com.izumi.domain.Employee;
import com.izumi.domain.vo.R;
import com.izumi.util.RedisUtils;
import com.izumi.web.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录认证拦截器
 *      对用户请求拦截
 *          验证是否登录，如果登录，方法
 *          如果没有登录，跳转到登录页面
 */
@Component
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //对请求验证
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
        String employeeJson = redisUtils.get(RedisConstant.LOGIN_USER_INFO + ":" + userId);
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper.readValue(employeeJson, Employee.class);
        //2.判断登录对象是否为null,如果为null，未登录
        if(employee == null){
            try{
                //401
                response.getWriter().write(mapper.writeValueAsString(
                        R.error(HttpStatus.UNAUTHORIZED.value(),new String("没有登录"))));
                return false;//false:拦截请求
            }catch (Exception e){
            }
        }
        //已登录,放行
        return true;
    }
}
