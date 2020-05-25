package com.sunwul.mqconsumer.rabbitmq.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.MessagingMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*****
 * @author: Sunwul
 * @date: 2020/3/2 10:12
 * PS: 主题交换机的反序列化
 */
@Configuration
public class TopicRabbitConfig {

    /**注入自定义反序列化规则*/
//    @Bean
//    public MessageConverter getMessageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }
}
