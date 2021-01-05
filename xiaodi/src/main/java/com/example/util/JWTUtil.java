package com.example.util;

import com.example.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT 工具类
 * 1.生成的token 是可以通过base 64 进行解密出明文信息
 * 2.base64 进行解密出明文信息  修改该在进行编码  则会  解密失败
 * 3.无法作废已颁布的token  除非改密钥
 */
public class JWTUtil {
    /**
     * 密钥过期时间
     */
    static final long EXPIRE = 60000 * 60 * 24 * 7;
//    static final long EXPIRE = 1;

    /**
     * 加密密钥
     */
    private static final String SECRET = "com.wang";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "xdclass";

    /**
     * 颁布 主题  subject
     */
    private static final String SUBJECT = "xdclass";


    /**
     * 根据用户信息生成令牌
     *
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user) {
        String token = Jwts.builder().setSubject(SUBJECT)     //   颁布者
                .claim("head_img", user.getHeadImg())
                .claim("id", user.getId())
                .claim("name", user.getName())  // pyload 头信息
                .setIssuedAt(new Date())   //当前时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))   //过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();   //加密方式  指定密钥

        token = TOKEN_PREFIX + token;

        return  token;
    }

    /**
     * jwt验证
     * @param token
     * @return
     */

    public  static Claims checkJwt(String token){
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return claims;
        }catch (Exception e){
           return  null;
        }



    }

}
