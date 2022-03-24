package com.example.store.mapper;

import com.example.store.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * 用户模块的持久层接口
 */
@Mapper
public interface UserMapper {
    /**
     *
     * @param user 用户的数据
     * @return  受影响的行数
     */
     Integer insert(User user);

     User findByUsername(String username);

     User findByUid(Integer uid);

    /**
     * 根据uid来修改用户密码
     * @param uid
     * @return  受影响的行数
     *         修改执行者，修改时间
     */
     Integer updatePasswordByUid(Integer uid, String password,
                                 String modified_user, Date modified_time);

}
