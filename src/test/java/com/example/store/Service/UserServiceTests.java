package com.example.store.Service;

import com.example.store.entity.User;
import com.example.store.service.IUserService;
import com.example.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {



    @Autowired
    private IUserService iUserService;

    User user=new User();


    @Test
    public void erg(){
        try {

            user.setUsername("aa");
            user.setPassword("1");
            iUserService.erg(user);
            System.out.println("OKOK");
        } catch (ServiceException e) {
            //获取类的对象，再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常具体描述信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){

        User login = iUserService.login("lisi", "sssss");
        System.out.println(login);
    }
    @Test
    public void changepssword(){
       iUserService.changePassword(7,"管理员","sssaa","sssss");
    }

    @Test
    public void getByuid(){
        User byuid = iUserService.getByuid(8);
        System.out.println(byuid);
    }
    @Test
    public void updateinfo(){


        try {
            User user = iUserService.getByuid(8);
            String username = user.getUsername();

            iUserService.updateInfo(8,user,username);
            System.out.println("okok");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void updateavatar(){
        iUserService.updateavatar(7,"/upload/avatar.png","管理员");
    }




}
