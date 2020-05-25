package com.sunwul.zuul;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Sunwul
 * @date 2020/1/14 14:20
 * PS Zuul转发中心
 * @Api 声明自己是一个Zuul服务
 * @EnableSwagger2 启用Swagger
 */
@Api(value = "ZuulApplication",tags = {"Zuul转发中心"})
@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}
