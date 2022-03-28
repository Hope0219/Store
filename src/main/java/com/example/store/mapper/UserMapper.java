package com.example.store.mapper;

import com.example.store.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @param uid    @Param对应sql语句中#{}中的值
     * @return  受影响的行数
     *         修改执行者，修改时间
     */
     Integer updatePasswordByUid(@Param("uid") Integer uid, @Param("password") String password,
                                @Param("modifieduser") String modified_user, @Param("modifiedtime") Date modified_time);


    /**
     * 修改个人资料
     * @param user
     * @return
     */
     Integer updateInfo(User user);
}
