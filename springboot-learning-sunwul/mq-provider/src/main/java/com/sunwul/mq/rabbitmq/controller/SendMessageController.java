package com.sunwul.mq.rabbitmq.controller;

import com.sunwul.mq.rabbitmq.entity.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/*****
 * @author: Sunwul
 * @date: 2020/2/28 15:31
 * PS: 消息发送控制
 */
@RestController
public class SendMessageController {

    /**set注入 优于@Autowired直接注入*/
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private void setRabbitTemplate(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;
    }

    /**推送消息至直连(DirectExchange)交换机*/
    @GetMapping("sendD")
    public String sendMessageToDirectExchange(Message message){
        //使用java的UUID工具生成消息的id
        message.setMessageId(String.valueOf(UUID.randomUUID()));
        //消息内容
        message.setMessageData("hello DirectExchange");
        //消息创建时间 设定格式
        message.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        rabbitTemplate.convertAndSend("sunwul.DirectExchange","DirectRouting",message.toString()+"from Direct");
        return "发送消息至DirectExchange!";
    }

    /**推送消息1至主题(TopicExchange)交换机*/
    @GetMapping("sendT")
    public String sendMessageToTopicExchange(Message message){
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageData("hello TopicExchange");
        message.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //路由键符合sunwul.#规则,所以Queue1和Queue2都会收到此条信息   *与扇形交换机的功能类似*
        rabbitTemplate.convertAndSend("sunwul.TopicExchange","sunwul.TopicRouting",message.toString()+"from TopicQueue1");
        return "发送消息至TopicExchange! 与扇形类似";
    }

    /**推送消息2至主题(TopicExchange)交换机 设置符合sunwul.#规则的路由键 */
    @GetMapping("sendT2")
    public String sendMessage2ToTopicExchange(Message message){
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageData("hello TopicExchange");
        message.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //路由键符合sunwul.#规则但不等于sunwul.TopicQueue1绑定的sunwul.TopicRouting,所以只有Queue2会收到此条消息   *与直连型交换机的功能类似*
        rabbitTemplate.convertAndSend("sunwul.TopicExchange","sunwul.TopicRouting2",message.toString()+"from TopicQueue2");
        return "发送消息至TopicExchange! 与直连类似";
    }

    /**推送消息至扇形(FanoutExchang)交换机*/
    @GetMapping("sendF")
    public String sendMessageToFanoutExchange(Message message){
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageData("hello FanoutExchang");
        message.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        rabbitTemplate.convertAndSend("sunwul.FanoutExchange",null,message.toString());
        return "发送消息至FanoutExchang";
    }

    /**消息确认回调测试 --交换机不存在 回调ConfirmCallback
     * 交换机与队列都不存在的情况回调与此处一致
     * */
    @GetMapping("callback1")
    public String callBack1(Message message){
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageData("hello");
        message.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        rabbitTemplate.convertAndSend("sunwul.null",null,message.toString());
        return "OK";
    }

    /**消息确认回调测试 --交换机存在,队列不存在 回调ConfirmCallback和RetrunCallback*/
    @GetMapping("callback2")
    public String callBack2(Message message){
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageData("hello");
        message.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //发送消息至存在的交换机 sunwul.CallbackDirectExchange ,此交换机未绑定队列
        rabbitTemplate.convertAndSend("sunwul.CallbackDirectExchange",null,message.toString());
        return "OK";
    }

    //消息推送成功(测试前面正确的消息推送方法就可以测试了) 回调ConfirmCallback
}
