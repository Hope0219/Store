package com.example.store.controller;

import com.example.store.controller.ex.*;
import com.example.store.entity.User;
import com.example.store.service.IUserService;
import com.example.store.service.ex.InsertException;
import com.example.store.service.ex.ServiceException;
import com.example.store.service.ex.UsernameDuplicatedException;
import com.example.store.util.JsonResult;
import com.sun.glass.ui.Size;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import javax.websocket.Session;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.deploy.util.BufferUtil.MB;

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
//        String upload = session.getServletContext().getRealPath("upload");
//        System.out.println(upload);
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
    public  static final List<String> AVATAR_TYPE=new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
        AVATAR_TYPE.add("image/jpg");
    }


    public static  final  int filesize=10*1024*1024;

    @Value("${web-upload-path}")
    private String path;

    //上传头像
    @RequestMapping("/change_avatar")
    public JsonResult<String> changeavatar(HttpSession httpSession, MultipartFile file)  {
        Integer uid = getuidFromSession(httpSession);
        String username = getUsernameFromSession(httpSession);
//        String path="\\src\\main\\resources\\static\\avator\\";
        if(file.isEmpty()){
            throw new FileEmptyException("文件不存在");
       }

        if(!AVATAR_TYPE.contains(file.getContentType()))
        {
            throw new FileTypeException("文件类型不符合:"+AVATAR_TYPE);
        }
        //获取文件初始名
        String originalFilename = file.getOriginalFilename();
       //获取文件扩展名                      获得文件从.+1开始的字符串
        String type= originalFilename.substring(originalFilename.indexOf(".")+1);

        //修改后的文件名
        String filename=System.currentTimeMillis()+"."+type;
        //定义转储的父目录
        //获取当前项目路径
        String filepath = System.getProperty("user.dir")+path;
        File dir=new File(filepath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
//        String filepath=        "C:\\Users\\Admin\\Desktop\\Hope\\Store\\src\\main\\resources\\static\\";filepath+"\\src\\main\\resources\\static
        //创建文件目录
        File dest=new File(dir,filename);
        //判断此文件是否存在
        if(!dest.getParentFile().exists())
        {
            //创建目录
            dest.getParentFile().mkdirs();

        }
        try {
            //将文件写入dest文件中
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadIOException();
        }
//        User user = iUserService.getByuid(uid);
//
//        String avatar=filepath+"\\src\\main\\resources\\static\\avator\\"+filename;

        String avatar="/avator/"+filename;
        iUserService.updateavatar(uid,avatar,username);

        System.out.println(dest);
        System.out.println(new JsonResult<String>(OK,avatar).toString());

        return new JsonResult<String>(OK,avatar);
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
