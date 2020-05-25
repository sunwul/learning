package com.sunwul.mq;

import com.sunwul.mq.activemq.controller.ProducerController;
import com.sunwul.mq.rabbitmq.controller.SendMessageController;
import com.sunwul.mq.rabbitmq.entity.Message;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jms.Destination;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class MqProviderApplicationTests {

    /**注入activemq.controller*/
    @Autowired
    ProducerController producerController;

    /**注入rabbitmq.controller*/
    @Autowired
    SendMessageController sendMessageController;

    @Test
    void contextLoads() {

        /**activeMQ测试*/
        //测试queue
//        for(int i=0; i<5; i++){
//            producerController.sendMessageToActiveMqQueue( "hello is queue!!!");
//        }

        //测试topic
//        for(int i=0;i<5;i++){
//            producerController.sendMessageToActiveMqTopic("hello is topic!!!");
//        }

        Message message=new Message();
//        message.setMessageId("123"); //定义的消息被覆盖
//        message.setMessageData("hello");
//        message.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        /**rabbitMQ测试*/
        //直连
//        for(int i=0;i<3;i++){
//            sendMessageController.sendMessageToDirectExchange(message);
//        }

        //主题  测试绑定键
//        for(int i=0;i<3;i++){
//            sendMessageController.sendMessageToTopicExchange(message);
//        }

//        sendMessageController.sendMessage2ToTopicExchange(message);

        //扇形
        sendMessageController.sendMessageToFanoutExchange(message);

        //回调函数测试 Callback1 交换机不存在
//        sendMessageController.callBack1(message);
        //回调函数测试 Callback2 交换机存在,队列不存在
//        sendMessageController.callBack2(message);
    }

}
