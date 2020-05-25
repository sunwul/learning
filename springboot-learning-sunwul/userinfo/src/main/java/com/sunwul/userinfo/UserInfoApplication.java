package com.sunwul.userinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: Sunwul
 * @date: 2019/12/30 18:40
 * @EnableEurekaClient 声明自己是Eureka客户端
 * @MapperScan 扫描指定包名下的Mapper
 * */
@MapperScan("com.sunwul.userinfo.mapper")
@EnableEurekaClient
@SpringBootApplication
public class UserInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserInfoApplication.class, args);
    }

}
