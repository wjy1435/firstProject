package com.example.service;

import com.example.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserService {

    int save(Map<String,String> userInfo);

    User findByPhone(@Param("phone") String phone);

    String finByPhoneAndPwd(String phone, String pwd);

    User finUserById(Integer id);
}
