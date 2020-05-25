package com.sunwul.redis.controller;


import com.sunwul.redis.bean.User;
import com.sunwul.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*****
 * @author: Sunwul
 * @date: 2020/2/18 22:09
 * PS: 
 */
@RestController
public class TestController {

    /**使用redisTemplate*/
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/adduserT")
    public void addUserT(){
        User userT=new User(1,"admin","男");
        //opsForValue 操作字符串
        ValueOperations<String,User> operations=redisTemplate.opsForValue();
        //opsForHash 操作hash
//        ValueOperations<String,User> operations=redisTemplate.opsForHash();
        //opsForList 操作List
//        ValueOperations<String,User> operations=redisTemplate.opsForList();
        //opsForSet 操作set
//        ValueOperations<String,User> operations=redisTemplate.opsForSet();
        //opsForZSet 操作有序set
//        ValueOperations<String,User> operations=redisTemplate.opsForZSet();
        //插入
        operations.set("userT",userT);
        boolean exists=redisTemplate.hasKey("userT");
        System.out.println("redis是否存在对应的key:"+exists);
        User getUserT=(User)operations.get("userT");
        System.out.println("从redis中获取的userT:"+getUserT.toString());
    }

    /**使用工具类*/
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/addname")
    public void addName(){
        //调用工具类存入数据
        redisUtil.set("name","sunwul");
        //取出
        Object object =redisUtil.get("name");
        System.out.println("从数据库中读取的数据: "+object);
    }

    @GetMapping("/adduser")
    public void addUser(){
        User user=new User(2,"sunwul","男");
        redisUtil.set("user",user);
        boolean exists=redisUtil.hasKey("user");
        System.out.println("redis是否存在对应的key:"+exists);
        User getUser=(User)redisUtil.get("user");
        System.out.println("从redis中取出的user:"+getUser.toString());
    }
}
