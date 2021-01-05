package com.example.mapper;

import com.example.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {


    int save(User user);

    User findByPhone(@Param("phone") String phone);

    User finByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);

    User finUserById(@Param("id") Integer id);
}
