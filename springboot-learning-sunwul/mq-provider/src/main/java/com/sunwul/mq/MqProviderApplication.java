package com.sunwul.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author Sunwul
 * @date 2020-02-22 23:34:00
 * activeMQ-- @EnableJms 加了此注解就是异步队列,没加就是同步队列
 */

@EnableJms
@SpringBootApplication
public class MqProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqProviderApplication.class, args);
    }

}
