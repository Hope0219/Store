package com.example.store.mapper;

import com.example.store.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户模块的持久层接口
 */
//@Mapper
public interface UserMapper {
    /**
     *
     * @param user 用户的数据
     * @return  受影响的行数
     */
     Integer insert(User user);

     User findByUsername(String username);
}
