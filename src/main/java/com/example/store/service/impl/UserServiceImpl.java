package com.example.store.service.impl;

import com.example.store.entity.User;
import com.example.store.mapper.UserMapper;
import com.example.store.service.IUserService;
import com.example.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Service //将当前类的对象交给Spring来管理，自动创建对象以及对象的维护
public class UserServiceImpl implements IUserService {

    @Autowired
   private UserMapper userMapper;

    @Override
    public void erg(User user) {
        //通过user参数来获取传递过来的username
        //通过findByUsername方法判断用户是否被注册过
        User byUsername = userMapper.findByUsername(user.getUsername());
        if (byUsername != null)
           throw new UsernameDuplicatedException("用户名已被注册！");
        else {
            //密码加密，MD5
            //（串+password+串）——MD5算法进行加密，连续加载3次
            //盐值+password+盐值——盐值是一个随机的字符串
            String password = user.getPassword();
            //获取一个盐值
            String salt = UUID.randomUUID().toString().toUpperCase();
            //补全随机的盐值
            user.setSalt(salt);
            //密码和盐值加密处理,忽略原有密码强度，提升了数据的安全性
            String md5Password = getMD5Password(salt, password);
            //将加密后的密码补全设置到user对象中
            user.setPassword(md5Password);
            user.setIs_delete(0);
            Date date = new Date();
            user.setCreated_time(date);
            user.setCreated_user(user.getUsername());
            user.setModified_time(date);
            user.setModified_user(user.getUsername());

            //注册用户（rows==1）
            Integer rows = userMapper.insert(user);
            if(rows != 1)
                throw new InsertException("用户注册过程中产生了未知的异常");
              System.out.println(rows);
        }
    }
    /** 定义一个MD5加密算法的处理*/
    private String getMD5Password(String salt,String password){
        for (int i = 0; i < 2; i++) {
            //MD5加密算法方法的调用
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toString().toUpperCase();

        }
         return  password;
    }

    @Override
    public User login(String username,String password) {
        User byUsername = userMapper.findByUsername(username);
        String password1 = byUsername.getPassword();
        if(byUsername!=null)
        {
            String salt = byUsername.getSalt();
            //将用户输入的密码进行相同的加密
            String md5Password = getMD5Password(salt, password);
            if(password1.equals(md5Password)){
                System.out.println("登陆成功");
            }else throw new PasswordNotMatchException("密码错误");
        }else if(byUsername.getIs_delete()==1)
            throw new UserNotFoundException("用户名不存在");
        //数据提取出来
        User user=new User();
        user.setUid(byUsername.getUid());
        user.setUsername(byUsername.getUsername());
        user.setAvatar(byUsername.getAvatar());

        return user;
    }

    @Override
    public void changePassword(Integer uid, String username,
                               String newpassword, String oldpassword) {
        User result = userMapper.findByUid(uid);
//        if (result == null || result.getIs_delete() == 1)
//            throw new UserNotFoundException("用户数据不存在");
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }

        // 检查查询结果中的isDelete是否为1
        if (result.getIs_delete().equals(1)) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldmd5Password = getMD5Password(result.getSalt(), oldpassword);
        if(!oldmd5Password.equals(result.getPassword())){
            throw new UpdateException("密码错误");
        }
        String password = getMD5Password(result.getSalt(), newpassword);
        Integer row = userMapper.updatePasswordByUid(uid, password, username, new Date());
        if(row!=1)
            throw new UpdateException("更新时产生未知数据的异常");
    }

    /**
     * 将修改之前的资料信息展现出来
     * @param uid
     * @return
     */
    @Override
    public User getByuid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        // 检查查询结果中的isDelete是否为1
        if (result.getIs_delete().equals(1)) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        User user=new User();
        user.setUsername(result.getUsername());
        user.setEmail(result.getEmail());
        user.setPhone(result.getPhone());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void updateInfo(Integer uid, User user, String username) {
        User result = userMapper.findByUid(uid);
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        // 检查查询结果中的isDelete是否为1
        if (result.getIs_delete().equals(1)) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModified_user(username);
        user.setModified_time(new Date());
        Integer row = userMapper.updateInfo(user);
        if (row != 1) {
            throw new UpdateException("更新异常");
        }
        System.out.println(row);
    }


}
