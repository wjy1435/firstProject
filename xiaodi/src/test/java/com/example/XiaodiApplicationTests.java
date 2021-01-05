package com.example;

import com.example.model.entity.User;
import com.example.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XiaodiApplicationTests {

    @Test
    void contextLoads() {


        User user =new User();
        user.setId(77);
        user.setName("王靖宇");
        user.setHeadImg("png");

        String token = JWTUtil.geneJsonWebToken(user);
        System.out.println(token);
        try {
            Thread.sleep(4000);
        }catch (Exception e){
            e.printStackTrace();
        }

        Claims claims = JWTUtil.checkJwt(token);

        System.out.println(claims.get("name"));
    }

}
