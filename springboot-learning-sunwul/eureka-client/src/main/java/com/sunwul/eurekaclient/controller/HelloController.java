package com.sunwul.eurekaclient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*****
 * @author: Sunwul
 * @date: 2020/1/13 23:14
 * PS: 
 */
@Api(value = "HelloController",tags = {"eureka测试elient"})
@RestController
public class HelloController {
    /**接收来至浏览器的 hello请求*/
    @ApiOperation(value = "测试方法-HelloWorld", notes = "测试eureka服务是否生效")
    @RequestMapping("/hello")
    public String hello(){
        return "eureka-client: Hello World!  AAAA";
    }
}
