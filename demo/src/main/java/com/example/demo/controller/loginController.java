package com.example.demo.controller;

import com.example.demo.entity.AyMood;
import com.example.demo.entity.AyUser;
import com.example.demo.error.BussinessException;
import com.example.demo.model.AyUserAttachmentRel;
import com.example.demo.producer.AyMoodProducer;
import com.example.demo.service.AyMoodService;
import com.example.demo.service.AyUserAttachmentRelService;
import com.example.demo.service.CommonService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;
import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
@CrossOrigin
@RestController
@EnableCaching
public class loginController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private  StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AyMoodService ayMoodService;

    @Autowired
    private AyMoodProducer ayMoodProducer;


    @Autowired
    private AyUserAttachmentRelService ayUserAttachmentRelService;
    //查询
    @RequestMapping(value = "/loginPage", method = {RequestMethod.POST, RequestMethod.GET})
    public  @ResponseBody Map login(@RequestBody AyUser user/*HttpServletRequest request, HttpSession session*/){
        String name = user.getName();
        String password  = user.getPassword();
//        System.out.println("用户名："+user.getName());
//        System.out.println("密码是："+user.getPassword());
//        AyUser ayUser = commonService.findByNameAndPasswordRetry("阿旺","123456");
//        List<AyUser> ayUser = commonService.findAll();
//        model.addAttribute("users",ayUser);
        System.out.println("哈哈哈哈");
        AyUser ayUser = commonService.login(name,password);
        Map map = new HashMap();
        map.put("1","1");
        System.out.print("第一次");
        return  map ;

//        return null;
    }

    @RequestMapping(value = "/insertMethod", method = {RequestMethod.POST, RequestMethod.GET})
    public  String  insertData(@RequestBody AyUser user/*HttpServletRequest request, HttpSession session*/){
//        String name = request.getParameter("name");
//        String password  = request.getParameter("password");
        System.out.println("用户名："+user.getName());
        System.out.println("密码是："+user.getPassword());
         commonService.insertData(user.getId(),user.getName(), user.getPassword());
        return "true";

//        return null;
    }

    @RequestMapping(value = "/updateMethod", method = {RequestMethod.POST, RequestMethod.GET})
    public  String  updateData(@RequestBody AyUser user/*HttpServletRequest request, HttpSession session*/){
//        String name = request.getParameter("name");
//        String password  = request.getParameter("password");
        System.out.println("用户名："+user.getName());
        System.out.println("密码是："+user.getPassword());
       commonService.updateData(user.getId(),user.getName(), user.getPassword());
        return "true";

//        return null;
    }

    @RequestMapping(value = "/deleteData ", method = {RequestMethod.POST, RequestMethod.GET})
    public  String  deleteData(@RequestBody AyUser user/*HttpServletRequest request, HttpSession session*/){
//        String name = request.getParameter("name");
//        String password  = request.getParameter("password");
//        System.out.println("用户名："+user.getName());
//        System.out.println("密码是："+user.getPassword());
        commonService.deleteData(user.getId());
        return "true";

//        return null;
    }
    @RequestMapping(value = "/getAllUser", method = {RequestMethod.POST, RequestMethod.GET})
    public  @ResponseBody List<AyUser> getAllUser(@RequestBody AyUser user/*HttpServletRequest request, HttpSession session*/) {
//        String name = request.getParameter("name");
//        String password  = request.getParameter("password");
//        System.out.println("用户名："+user.getName());
//        System.out.println("密码是："+user.getPassword());
//        AyUser ayUser = commonService.findByNameAndPasswordRetry("阿旺","123456");
//        List<AyUser> ayUser = commonService.findAll();
//        model.addAttribute("users",ayUser);
        System.out.println("哈哈哈哈");
        List<AyUser> list = commonService.getAllUser();
        return list;
    }

    @RequestMapping(value ="/jpaFinfById",method={RequestMethod.POST, RequestMethod.GET})
    public  @ResponseBody AyUser  testJpa(@RequestBody AyUser user){
        String id = user.getId();
//        String name = user.getName();
//        String password  = user.getPassword();
        AyUser  tempUser = new AyUser();
//        tempUser = commonService.findById(id);
        List<AyUser> list = new ArrayList<>();
        System.out.println(tempUser.getName());
        return tempUser;
    }

    @RequestMapping(value ="/testJpaFindfAll",method={RequestMethod.POST, RequestMethod.GET})
    public  @ResponseBody List<AyUser>  testJpaFindfAll(@RequestBody AyUser user){
//        String id = user.getId();
//        String name = user.getName();
//        String password  = user.getPassword();
//        AyUser  tempUser = new AyUser();
//        tempUser = commonService.findById(id);
        List<AyUser> list = new ArrayList<>();
        list = commonService.findAll();
//        System.out.println(tempUser.getName());
        return list;
    }

    @Transactional
    @RequestMapping(value ="/testJpaSave",method={RequestMethod.POST, RequestMethod.GET})
    public  String testJpaSave(@RequestBody AyUser user){
        String id = user.getId();
        String name = user.getName();
        String password  = user.getPassword();
//        AyUser  tempUser = new AyUser();
//        tempUser = commonService.findById(id);
//        List<AyUser> list = new ArrayList<>();
        user = commonService.save(user); //事务
//        System.out.println(tempUser.getName());
        String erro  = null;
        erro.split("/");
        return "true";
    }

    @RequestMapping(value ="/testJpaDelete",method={RequestMethod.POST, RequestMethod.GET})
    public  String testJpaDelete(@RequestBody AyUser user){
        String id = user.getId();
        String name = user.getName();
        String password  = user.getPassword();
//        AyUser  tempUser = new AyUser();
//        tempUser = commonService.findById(id);
//        List<AyUser> list = new ArrayList<>();
         commonService.delete(id);
//        System.out.println(tempUser.getName());
        return "true";
    }

    @RequestMapping(value ="/testJpaPage",method={RequestMethod.POST, RequestMethod.GET})
    public  @ResponseBody Page<AyUser> testJpaPage(@RequestBody AyUser user){
//        String id = user.getId();
//        String name = user.getName();
//        String password  = user.getPassword();
//        AyUser  tempUser = new AyUser();
//        tempUser = commonService.findById(id);
        List<AyUser> list = new ArrayList<>();
//        commonService.delete(id);
//        System.out.println(tempUser.getName());
        PageRequest  pageRequest = new PageRequest(1,2);
        Page<AyUser> userList5 = commonService.findAll(pageRequest);
        return userList5;
    }

    @RequestMapping(value ="/testRedis",method={RequestMethod.POST, RequestMethod.GET})
    public String testRedis(){
        setSerializer(redisTemplate);
//        redisTemplate.setDefaultExpiration(60);
        redisTemplate.opsForValue().set("names","798999999");
//        String name = (String)redisTemplate.opsForValue().get("name");
//        System.out.println(name);
//        redisTemplate.delete(name);66666666666666666655555555555555555555555555555
//        redisTemplate.opsForValue().set("name","aaa");
        String name = (String) redisTemplate.opsForValue().get("names");
        System.out.println(name);
        return "trues";
    }

    @RequestMapping(value ="/testMq",method={RequestMethod.POST, RequestMethod.GET})
    public String testMq(){
//
        AyMood  ayMood = new AyMood();
        ayMood.setId("1");
        ayMood.setUserId("1");
        ayMood.setPraiseNum(0);
        ayMood.setContent("第一条微信 说说");
        long time = System.currentTimeMillis();
        String timeStr = String.valueOf(time);
        ayMood.setPublishTime(timeStr);

        AyMood mood = ayMoodService.save(ayMood);
        return "trues";
    }

    @RequestMapping(value ="/testMq1",method={RequestMethod.POST, RequestMethod.GET})
    public String testMq1(){
        Destination  destination = new ActiveMQQueue("ay.queue");
        ayMoodProducer.sendMessage(destination,"hello,mq!!!");
        System.out.println("=========================================");
        return "trues";
    }
    @RequestMapping(value ="/testMq2",method={RequestMethod.POST, RequestMethod.GET})
    public String testMq2(){
        AyMood  ayMood = new AyMood();
        ayMood.setId("2");
        ayMood.setUserId("1");
        ayMood.setPraiseNum(0);
        ayMood.setContent("第一条微信 说说");
        long time = System.currentTimeMillis();
//        String timeStr = String.valueOf(time);
//        ayMood.setPublishTime(String.valueOf(time));
        String msg = ayMoodService.asynSave(ayMood);
        System.out.println("异步发表说说"+msg);
        System.out.println("=========================================22222222222222222222222222");
        return "trues";
    }

    @RequestMapping(value ="/testAsync",method={RequestMethod.POST, RequestMethod.GET})
    public String findAll(){
        long startTime = System.currentTimeMillis();
        System.out.println("第一次查询所有用户");
//        List<AyUser> ayUserList1 = commonService.findAll();
        Future<List<AyUser>> ayUserList1 = commonService.findAsynAll();


        System.out.println("第二次查询所有用户");
//        List<AyUser> ayUserList2 = commonService.findAll();
        Future<List<AyUser>> ayUserList2 = commonService.findAsynAll();
        System.out.println("第三次查询所有用户");
//        List<AyUser> ayUserList3 = commonService.findAll();
        Future<List<AyUser>> ayUserList3 = commonService.findAsynAll();
        try {

            while(true){
                if(ayUserList1.isDone() && ayUserList2.isDone() && ayUserList3.isDone()){
                    break;
                }else{
                    Thread.sleep(10);

                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("完成耗时"+(end - startTime)+"毫秒");

        return "success";
    }

//    @RequestMapping(value ="/findAll",method={RequestMethod.POST, RequestMethod.GET})
//    public String findAll(Model model){
//        List<AyUser> ayUser = commonService.findAll();
////        List<AyUser> ayUser = commonService.findAll();
//        model.addAttribute("users",ayUser);
//        throw new BussinessException("业务异常");
//
//
//
//    }

//    @RequestMapping(value ="/findByNameAndPasswordRetry",method={RequestMethod.POST, RequestMethod.GET})
//    public String findByNameAndPasswordRetry(Model model){
//     AyUser ayUser = commonService.findByNameAndPasswordRetry("阿旺","123456");
////        List<AyUser> ayUser = commonService.findAll();
//        model.addAttribute("users",ayUser);
////        throw new BussinessException("业务异常");
//
//        return "success";
//
//    }


    @RequestMapping(value ="/testMongodb",method={RequestMethod.POST, RequestMethod.GET})
    public String testMongodb(){
        AyUserAttachmentRel ayUserAttachmentRel = new AyUserAttachmentRel();
        ayUserAttachmentRel.setId("1");
        ayUserAttachmentRel.setUserId("1");
        ayUserAttachmentRel.setFilename("简历");
        ayUserAttachmentRelService.save(ayUserAttachmentRel);
        System.out.println("保存成功");
        return "success";

    }



//    @RequestMapping(value ="/testAsync",method={RequestMethod.POST, RequestMethod.GET})
//    public String findAllAsyn(){
//        long startTime1 = System.currentTimeMillis();
//        System.out.println("第一次查询所有用户1");
//        List<AyUser> ayUserList11 = commonService.findAll();
//        System.out.println("第二次查询所有用户");
//
//        List<AyUser> ayUserList22 = commonService.findAll();
//        System.out.println("第三次查询所有用户");
//        List<AyUser> ayUserList33 = commonService.findAll();
//        long end = System.currentTimeMillis();
//        System.out.println("完成耗时"+(end - startTime)+"毫秒");
//        return "success";
//    }






    private void setSerializer(RedisTemplate template){
//        @SuppressWarnings({ "rawtypes", "unchecked" })
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }


}
