package com.sunwul.mqconsumer.rabbitmq.config;

import com.sunwul.mqconsumer.rabbitmq.component.RabbitMqDirectConsumer;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*****
 * @author: Sunwul
 * @date: 2020/3/2 16:48
 * PS: 收到消息的消息确认机制
 * 1,自动确认(默认) AcknowledgeMode.NONE
 * RabbitMQ成功将消息发出（即将消息成功写入TCP Socket）即认为本次投递已经被正确处理,不管消费者端是否成功处理本次投递.
 * 这种情况如果消费端消费逻辑抛出异常,也就是消费端没有处理成功这条消息,那么就相当于丢失了消息.
 * 一般这种情况都是使用try catch捕捉异常后,打印日志用于追踪数据,这样找出对应数据再做后续处理.
 * 2,手动确认 AcknowledgeMode.MANUAL
 * 消费者收到消息后,手动调用basic.ack/basic.nack/basic.reject,RabbitMQ收到这些消息后,才认为本次投递成功.
 * basic.ack用于肯定确认
 * basic.nack用于否定确认（注意：这是AMQP 0-9-1的RabbitMQ扩展） 
 * basic.reject用于否定确认,但与basic.nack相比有一个限制:一次只能拒绝单条消息 
 * 消费者端以上的方法都表示消息已经被正确投递,但是basic.ack表示消息已经被正确处理,
 * 但是basic.nack,basic.reject表示没有被正确处理,但是RabbitMQ中仍然需要删除这条消息
 */
@Configuration
public class MessageListenerConfig {


    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    public void setCachingConnectionFactory(CachingConnectionFactory cachingConnectionFactory){
        this.cachingConnectionFactory=cachingConnectionFactory;
    }

    @Autowired
    private RabbitMqDirectConsumer rabbitMqDirectConsumer;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer=new SimpleMessageListenerContainer(cachingConnectionFactory);
        simpleMessageListenerContainer.setConcurrentConsumers(1);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(1);
        // RabbitMQ默认是自动确认，这里改为手动确认消息
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setQueueNames("sunwul.DirectQueue");
        simpleMessageListenerContainer.setMessageListener(rabbitMqDirectConsumer);
        return simpleMessageListenerContainer;
    }
}
