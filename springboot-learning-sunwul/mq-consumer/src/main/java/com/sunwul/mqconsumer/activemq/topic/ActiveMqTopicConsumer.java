package com.sunwul.mqconsumer.activemq.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/*****
 * @author: Sunwul
 * @date: 2020/2/26 15:47
 * PS: 消息消费者 topic
 */
@Component
public class ActiveMqTopicConsumer {

    /**topic 发布订阅模式：发布者发送到topic的消息，只有订阅了topic的订阅者才会收到消息
     * 从0到许多个订阅者都能得到一个消息的拷贝
     * 只有在消息代理收到消息时有一个有效订阅时的订阅者才能得到这个消息的拷贝
     * #默认情况下activemq提供的是queue模式(需注销以下配置)，若要使用topic模式需要配置下面配置
     * spring.jms.pub-sub-domain: true
     * */
//    @JmsListener(destination = "sunwul.topic")
    @SendTo("sunwul.queue")
    public String receive1(String message){
        System.out.println("topic消费者1接收到的消息: "+message);
        return "return : 成功接收消息: "+message;
    }

//    @JmsListener(destination = "sunwul.topic")
    @SendTo("sunwul.queue")
    public String receive2(String message){
        System.out.println("topic消费者2接收到的消息: "+message);
        return "return : 成功接收消息: "+message;
    }

//    @JmsListener(destination = "sunwul.topic")
    @SendTo("sunwul.queue")
    public String receive3(String message){
        System.out.println("topic消费者3接收到的消息: "+message);
        return "return : 成功接收消息: "+message;
    }

}
