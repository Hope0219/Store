package com.example.store.mapper;

import com.example.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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
}
