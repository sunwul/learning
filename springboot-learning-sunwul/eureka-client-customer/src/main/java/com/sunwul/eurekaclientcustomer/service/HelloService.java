package com.sunwul.eurekaclientcustomer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*****
 * @author: Sunwul
 * @date: 2020/1/15 13:38
 * PS: 
 */
@Service
public class HelloService {

    /**自动注入负载均衡ribbon对象*/
    @Autowired
    RestTemplate restTemplate;

    /**熔断错误回调*/
    public String HystixFailBack(){
        return "Hystix Error - 熔断发生错误";
    }

    /**注解指定发生错误时需要回调的方法*/
    @HystrixCommand(fallbackMethod = "HystixFailBack")
    public String helloService(){
        // Get请求调用服务，restTemplate被@LoadBalanced注解标记，Get方法会自动进行负载均衡
        // restTemplate会交替调用service_a和service_b
        return restTemplate.getForObject("http://eureka-client-hello/hello",String.class);
    }

}
