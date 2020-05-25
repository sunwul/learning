package com.sunwul.mq.activemq.config;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/*****
 * @author: Sunwul
 * @date: 2020/2/25 22:43
 * PS: 
 */
@Configuration
public class ActiveMqConfig {

    /**定义队列名*/
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("sunwul.queue");
    }
    /**主题名*/
    @Bean
    public Topic topic() {
        return new ActiveMQTopic("sunwul.topic");
    }

}
