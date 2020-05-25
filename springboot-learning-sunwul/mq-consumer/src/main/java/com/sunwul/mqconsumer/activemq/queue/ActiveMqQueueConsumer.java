package com.sunwul.mqconsumer.activemq.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/*****
 * @author: Sunwul
 * @date: 2020/2/25 22:49
 * PS: 消息消费者 queue
 */
@Component
public class ActiveMqQueueConsumer {

    /**
     * queue 点对点模式：生产者发送一条消息到queue，只有一个消费者能收到
     * 当没有消费者可用时,这个消息会被保存直到有 一个可用的消费者
     * 一个queue可以有很多消费者，他们之间实现了负载均衡
     * #默认情况下activemq提供的是queue模式(需注销以下配置)，若要使用topic模式需要配置下面配置
     * spring.jms.pub-sub-domain: true
     * */

    //与rabbitMQ有冲突 注释JmsListener
//    @JmsListener(destination = "sunwul.queue")
    public void receive1(String message){
        System.out.println("queue消费者1接收到的消息: "+message);
    }

//    @JmsListener(destination = "sunwul.queue")
    public void receive2(String message){
        System.out.println("queue消费者2接收到的消息: "+message);
    }

//    @JmsListener(destination = "sunwul.queue")
    public void receive3(String message){
        System.out.println("queue消费者3接收到的消息: "+message);
    }

}
