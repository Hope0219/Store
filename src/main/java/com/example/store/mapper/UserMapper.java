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

    /**
     * @Param("SQL映射文件中#{}占位符的变量名")：当SQL语句的占位符和映射的接口方法参
     * 数名不一致时，需要将某个参数强行注入到某个占位符变量上
     * 时，可以使用@Param,来标注映射关系
     * @param uid
     * @param Avatar
     * @param modifiedUser
     * @param modifiedtime
     * @return  受影响的行数
     */
     Integer updateAvatarByUid(@Param("uid") Integer uid,@Param("avatar") String Avatar,
                                @Param("modifieduser") String modifiedUser,@Param("modifiedtime") Date modifiedtime);
}
