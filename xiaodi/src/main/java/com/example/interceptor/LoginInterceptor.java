package com.example.interceptor;


import com.example.util.JWTUtil;
import com.example.util.JsonData;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mysql.cj.util.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 进入到controller 之前的方法
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("token");
            if (token == null) {
                token=request.getParameter("token");

            }
            if (token != null) {
                Claims claims = JWTUtil.checkJwt(token);
                if (claims == null) {
                    //登录过期
                    sendJsonMessage(response, JsonData.buildError("登录过期 重新登录"));
                }
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");
                request.setAttribute("name", name);
                request.setAttribute("id", id);
                return true;
            }else{
                sendJsonMessage(response,JsonData.buildError("请先登录,然后进行操作"));
            }
        } catch (Exception e) {
            sendJsonMessage(response, JsonData.buildError("登录过期  重新登录"));
        }
        return false;
    }

    public static void sendJsonMessage(HttpServletResponse response, Object object) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(object));
            writer.close();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
