package com.sunwul.eurekaclientcustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sunwul
 * @date 2020/1/15 13:35
 * @EnableCircuitBreaker   开启断路器功能, 进行容错管理
 */
@EnableCircuitBreaker
@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientCustomerApplication {

    /**注册一个具有容错功能的RestTemplate*/
    @Bean
    @LoadBalanced
    /**开启负载均衡客户端*/
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientCustomerApplication.class, args);
    }

}
