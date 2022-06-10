package com.example.store.config;

import com.example.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

//处理器拦截器的注册
@Configuration  //加载当前的拦截器并进行注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    //创建自定义拦截器的对象
    HandlerInterceptor interceptor=new LoginInterceptor();


    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建白名单
        List<String> pattern=new ArrayList<>();

        pattern.add("/bootstrap3/**");
        pattern.add("/css/**");
        pattern.add("images/**");
        pattern.add("/js/**");
        pattern.add("/web/login.html");
        pattern.add("/web/register.html");
        pattern.add("/web/index.html");
        pattern.add("/web/product.html");
        pattern.add("/users/reg");
        pattern.add("/users/login");
        pattern.add("/district/**");

        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(pattern);  //要拦截的url是什么

    }
}
