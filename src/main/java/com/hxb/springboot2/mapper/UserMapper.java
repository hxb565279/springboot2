package com.hxb.springboot2.mapper;


import com.hxb.springboot2.entity.UserBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select(" SELECT * FROM user where username = #{username} and password = #{password}  limit 1")
    UserBean getInto(String username, String password);

    @Select("select * from user where username= #{username}")
    UserBean getUsername(String username);

    @Insert("insert into user (username,password) values(#{username},#{password})")
    void registerUser(String username, String password);

    @Select("select * from user")
    List<UserBean> selectAllUser();

    @Select("select * from user where id = #{id}")
    UserBean getUserById(int id);

    @Update("update user set username= #{username} , password= #{password} where id =#{id}")
    int updateCustomer1(UserBean userBean);


    @Delete("delete from user where id = #{id}")
    int deleteUser(int id);
}
