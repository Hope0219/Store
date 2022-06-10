package com.example.store.mapper;

import com.example.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;
import java.util.Date;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;



    @Test
    public void insert()
    {
        User user=new User();
        user.setUsername("zhangsan");
        user.setPassword("sss123");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void findByUid(){

        User byUid = userMapper.findByUid(1);
        System.out.println(byUid);
    }

    @Test
    public void updatepassword(){

        User byUid = userMapper.findByUid(1);
        Integer integer = userMapper.updatePasswordByUid(1,
                "aa", "管理员",
                new Date());
        System.out.println(integer);
        System.out.println(byUid);
    }

    @Test
    public void updateinfo(){
        User user=new User();
        user.setUid(8);
        user.setPhone("17858802222");
        user.setEmail("admin@cy.com");
        user.setGender(1);
        user.setModified_user("系统管理员");
        user.setModified_time(new Date());
        Integer row = userMapper.updateInfo(user);
        System.out.println(row);
    }
    @Test
    public void update(){
        userMapper.updateAvatarByUid(7,"/upload/avatar.png","管理员",new Date());


    }


}
