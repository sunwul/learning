package com.sunwul.mqconsumer.rabbitmq.component;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/*****
 * @author: Sunwul
 * @date: 2020/3/2 10:18
 * PS: 主题交换机 消费者
 */
@Component
public class RabbitMqTopicConsumer {

    /**监听队列1*/
    @RabbitListener(queues = "sunwul.TopicQueue1")
    @RabbitHandler
    public void topicMessageConsumer1(String message){
        System.out.println("Topic消费者1接收到的消息: "+message);
    }

    @RabbitListener(queues = "sunwul.TopicQueue1")
    @RabbitHandler
    public void topicMessageConsumer2(String message){
        System.out.println("Topic消费者2接收到的消息: "+message);
    }

    @RabbitListener(queues = "sunwul.TopicQueue1")
    @RabbitHandler
    public void topicMessageConsumer3(String message){
        System.out.println("Topic消费者3接收到的消息: "+message);
    }

    /**监听队列2*/
    @RabbitListener(queues = "sunwul.TopicQueue2")
    public void topic2MessageConsumer1(String message){
        System.out.println("Topic2消费者1接收到的消息: "+message);
    }

    @RabbitListener(queues = "sunwul.TopicQueue2")
    public void topic2MessageConsumer2(String message){
        System.out.println("Topic2消费者2接收到的消息: "+message);
    }

    @RabbitListener(queues = "sunwul.TopicQueue2")
    public void topic3MessageConsumer1(String message){
        System.out.println("Topic2消费者3接收到的消息: "+message);
    }
}
