package com.example.store.service;

import com.example.store.entity.User;
import org.springframework.stereotype.Component;

//用户模块业务层接口

public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户的数据对象
     */
    void erg(User user);

    /**
     *
     * @param username
     * @param password
     * @return 当前匹配的用户数据，没有返回无
     */
    User login(String username,String password);

    void changePassword(Integer uid,String username,
                        String newpassword,
                        String oldpassword);
}
