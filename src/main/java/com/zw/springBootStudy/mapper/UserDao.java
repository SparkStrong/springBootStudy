package com.zw.springBootStudy.mapper;

import com.zw.springBootStudy.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Nack on 2018/4/4.
 */
public interface UserDao {

    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "userName", column = "user_name"),
            @Result(property = "email", column = "email")
    })
    List<User> getAll();

    @Select("SELECT * FROM user WHERE user_name = #{userName}")
    @Results({
            @Result(property = "userName", column = "user_name"),
            @Result(property = "email", column = "email")
    })
    User getUserByUserName(String userName);

    @Select("SELECT * FROM user WHERE user_name = #{userName} AND password = #{password}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "email", column = "email")
    })
    User getUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
}
