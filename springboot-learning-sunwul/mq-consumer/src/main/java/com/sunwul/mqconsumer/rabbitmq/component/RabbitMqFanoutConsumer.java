package com.sunwul.mqconsumer.rabbitmq.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*****
 * @author: Sunwul
 * @date: 2020/3/2 15:06
 * PS: 扇形交换机 消费者
 */
@Component
public class RabbitMqFanoutConsumer {

    /**
     * 三个队列(A,B,C)都绑定了sunwul.FanoutExchange这个扇形交换机
     * 所有发送到这个扇型交换机的消息,三个消息接收类都会监听到这条消息
     * */

    @RabbitListener(queues = "sunwul.FanoutA")
    public void fanoutMessageConsumer1(String message){
        System.out.println("FanoutA消费者1收到的消息"+message);
    }

    @RabbitListener(queues = "sunwul.FanoutB")
    public void fanoutMessageConsumer2(String message){
        System.out.println("FanoutB消费者1收到的消息"+message);
    }

    @RabbitListener(queues = "sunwul.FanoutC")
    public void fanoutMessageConsumer3(String message){
        System.out.println("FanoutC消费者1收到的消息"+message);
    }
}
