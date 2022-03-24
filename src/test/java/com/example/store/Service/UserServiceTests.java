package com.example.store.Service;

import com.example.store.entity.User;
import com.example.store.service.IUserService;
import com.example.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
