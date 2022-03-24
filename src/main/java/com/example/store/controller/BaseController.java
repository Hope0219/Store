package com.example.store.controller;

import com.example.store.service.ex.*;
import com.example.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/** 控制层的基类*/
public class BaseController {
    //操作成功的状态码
    public static final int OK=200;

    //请求处理方法，这个方法的返回值就是需要传给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    //当前项目中产生了异常，被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给前端
    @ExceptionHandler(ServiceException.class) //统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> jsonResult=new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException) {
            jsonResult.setState(4000);
            jsonResult.setMessage("用户名已被占用");
        }else if(e instanceof InsertException){
            jsonResult.setState(5000);
            jsonResult.setMessage("注册时产生位置的异常");
        }else if(e instanceof PasswordNotMatchException){
            jsonResult.setState(5001);
            jsonResult.setMessage("密码错误");
        }else if(e instanceof UserNotFoundException){
            jsonResult.setState(5002);
            jsonResult.setMessage("用户名不存在");
        }else if(e instanceof UpdateException){
            jsonResult.setState(5003);
            jsonResult.setMessage("密码错误");
        }
        return jsonResult;
    }
    /**
     * 获取session对象中的uid
     * @param session  session对象
     * @return  username值 当前登录的uid值
     */
    protected final Integer getuidFromSession(HttpSession session){
      return   Integer.valueOf(session.getAttribute("uid").toString());

    }

    /**
     * 获取当前用户登陆的username
     * @param session  session对象
     * @return  username值 当前登录的用户名
     * 在实现类中重写父类的tostring方法，不是句柄信息的输出
     */
    protected final String  getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}
