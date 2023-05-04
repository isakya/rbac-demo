package com.izumi.config;

import com.izumi.web.interceptor.CheckPermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private CheckPermissionInterceptor checkPermissionInterceptor;

    /**
     * 1 把自己拦截器交给springmvc管理
     * 2 配置拦截规则
     * 3 配置排除规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkPermissionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/code", "/api/login");
    }
}
