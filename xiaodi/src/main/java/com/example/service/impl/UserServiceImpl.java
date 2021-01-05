package com.example.service.impl;

import com.example.model.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.example.util.CommonUtil;
import com.example.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public int save(Map<String, String> userInfo) {

        User user = parseToUser(userInfo);
        if (user != null) {
            return userMapper.save(user);
        }else {
            return -1;
        }
//        return 0;
    }

    private User parseToUser(Map<String, String> userInfo) {
        User user = new User();
        if (userInfo.containsKey("name") && userInfo.containsKey("pwd") && userInfo.containsKey("phone")) {
            user.setName(userInfo.get("name"));
            user.setCreateTime(new Date());
            user.setHeadImg(CommonUtil.getRandomImg());
            user.setPhone(userInfo.get("phone"));
            String pwd = userInfo.get("pwd");
            System.out.println("pwd === "+pwd);
            user.setPwd(CommonUtil.MD5(pwd));
        } else {
            return null;
        }

        return user;
    }

    @Override
    public User findByPhone(String phone) {
        return null;
    }

    @Override
    public String finByPhoneAndPwd(String phone, String pwd) {

       User user =  userMapper.finByPhoneAndPwd(phone,CommonUtil.MD5(pwd));

       if (user == null){
           return  null;
       }else{
           String token = JWTUtil.geneJsonWebToken(user);
           return token;
       }


    }

    @Override
    public User finUserById(Integer id) {
        User user = userMapper.finUserById(id);
        user.setPwd("");
        return user;
    }
}
