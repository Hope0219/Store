package com.example.store.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//==AOP
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 在调用所有请求处理之前被自动调用执行的方法
     * 检测全局session对象中是否有uid数据，有则放行；没有则返回login.html登陆页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器（url+controller：映射）
     * @return true：放行当前请求  false：拦截当前请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //HttpServletRequest对象获取session对象
       Object obj = request.getSession().getAttribute("uid");
        if (obj == null) {
            //用户不存在，重定向到login.html页面
            response.sendRedirect("/web/login.html");
            //拦截
            return false;
        }
        return true;
    }

    /**
     * 在ModelandView对象返回之后被调用的方法
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 在整个请求所有关联的资源被执行完毕最后执行的方法
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
