package com.sunwul.mq.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/*****
 * @author: Sunwul
 * @date: 2020/2/25 22:41
 * PS: 队列消息控制器
 */
@RestController
public class ProducerController {

    //注入存放消息的队列
    //filed变量注入

//    @Autowired
//    private Topic topic;

    //set方法注入

    private Queue queue;
    @Autowired
    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    private Topic topic;
    @Autowired
    public void setTopic(Topic topic){
        this.topic=topic;
    }

    //注入springboot封装的工具类

//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;

    //set方法注入

    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    public void setJmsMessagingTemplate(JmsMessagingTemplate jmsMessagingTemplate){
        this.jmsMessagingTemplate=jmsMessagingTemplate;
    }


    /**queue -sendActiveMqQueue*/
    @RequestMapping("/sendAMQ")
    public void sendMessageToActiveMqQueue(String msg){
        //指定消息发送的目的地以及内容
        jmsMessagingTemplate.convertAndSend(this.queue,msg);
    }

    /**topic -sendActiveMqTopic*/
    @RequestMapping("/sendAMT")
    public void sendMessageToActiveMqTopic(String msg){
        jmsMessagingTemplate.convertAndSend(this.topic,msg);
    }
}
