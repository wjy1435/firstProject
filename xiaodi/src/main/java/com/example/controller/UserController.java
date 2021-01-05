package com.example.controller;

import com.example.model.entity.User;
import com.example.model.request.LoginRequest;
import com.example.service.UserService;
import com.example.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("register")
    public JsonData register(@RequestBody Map<String, String> userInfo) {
        // 影响行数
        int rows = userService.save(userInfo);

        return rows == 1 ? JsonData.buildSuccess() : JsonData.buildError("注册失败请重试");
    }


    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest) {

        String token = userService.finByPhoneAndPwd(loginRequest.getPhone(), loginRequest.getPwd());

        return token == null ? JsonData.buildError("登录失败") : JsonData.buildSuccess(token);
    }

    /**
     * 根据用户id 查询用户信息
     * @param request
     * @return
     */
    @GetMapping("findUserById")
    public JsonData findUserById(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("id");

        if (id == null) {
            return JsonData.buildError("未查询到该用户信息");
        }
        User user = userService.finUserById(id);


        return  JsonData.buildSuccess(user);
    }
}
