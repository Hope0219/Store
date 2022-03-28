package com.example.store.controller;

import com.example.store.entity.User;
import com.example.store.service.IUserService;
import com.example.store.service.ex.InsertException;
import com.example.store.service.ex.ServiceException;
import com.example.store.service.ex.UsernameDuplicatedException;
import com.example.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

//@Controller
@RestController  //==@Controller+@ResposeBody
@RequestMapping("/users")
public class UserController extends BaseController{
    @Autowired
    private IUserService iUserService;

    /**
     * 约定大于配置：开发思想来完成，省略大量的配置注解
     * 1.接收数据方式：请求处理方法的参数列表设置为pojo类型来接收前端的数据，
     * SpringBoot会将前端的url地址中的参数名和pojo类的属性名进行比较，如果
     * 这两个名称相同，则将值注入到pojo类中对应的属性上
     */

    @RequestMapping("/reg")
//    @ResponseBody //此方法的响应结果以json格式进行数据的响应给到前端
    public JsonResult<Void> reg(User user){
        iUserService.erg(user);
        return new JsonResult<>(OK);
    }
    /**
     *
     * 1.接收数据方式：请求处理方法的参数列表设置为非pojo类型，SpringBoot会直接将请求的参数名
     * 和方法的参数名进行比较，如果名称相同则自动完成值的依赖注入
     *
     *
     */
    @RequestMapping("/login")
    public JsonResult<User> login(String username,String password,HttpSession session){
        User data = iUserService.login(username, password);
        //向session对象中完成数据的绑定
       session.setAttribute("uid",data.getUid());
       session.setAttribute("username",data.getUsername());
       //得到绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        String upload = session.getServletContext().getRealPath("upload");
        System.out.println(upload);
        return new JsonResult<User>(OK,data);

    }
    @RequestMapping("/change_password")
    public JsonResult<Void> updatepassword(HttpSession httpSession,
                                           String oldPassword ,String newpassword
                                           ){
        Integer uid = getuidFromSession(httpSession);
        String username = getUsernameFromSession(httpSession);
        iUserService.changePassword(uid,username,newpassword,oldPassword);
         return new JsonResult<Void>(OK);
    }

    @RequestMapping("/get_byuid")
    public JsonResult<User> getbyuid(HttpSession session){
        Integer uid = getuidFromSession(session);
        User user = iUserService.getByuid(uid);
        return new JsonResult<User>(OK,user);

    }

    @RequestMapping("/change_Info")
    public JsonResult<Void> changeinfo(HttpSession httpSession,User user) {
        Integer uid = getuidFromSession(httpSession);
        String username = getUsernameFromSession(httpSession);
        iUserService.updateInfo(uid,user,username);
        return new JsonResult<>(OK);
    }
    /*
    @RequestMapping("/reg")
//    @ResponseBody //此方法的响应结果以json格式进行数据的响应给到前端
    public JsonResult<Void> reg(User user){
        //创建响应结果对象
        JsonResult<Void> jsonResult=new JsonResult<>();
        try {
            iUserService.erg(user);
            jsonResult.setState(200);
            jsonResult.setMessage("用户注册成功");
        } catch (UsernameDuplicatedException e) {
            jsonResult.setState(400);
            jsonResult.setMessage("用户名被占用");
        }  catch(InsertException e){
            jsonResult.setState(5000);
            jsonResult.setMessage("注册时产生未知的结果异常");
        }
         return jsonResult;
    }  */

}
