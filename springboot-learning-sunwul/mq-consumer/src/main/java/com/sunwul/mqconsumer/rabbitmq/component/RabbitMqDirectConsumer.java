package com.sunwul.mqconsumer.rabbitmq.component;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/*****
 * @author: Sunwul
 * @date: 2020/2/28 16:24
 * PS: 直连型交换机 消费者
 * @RabbitListener (queues = "sunwul.DirectQueue")  监听的队列
 * @RabbitHandler 暂未搞明白此注解的用处 貌似和异常处理有关
 */
@Component
@RabbitListener(queues = "sunwul.DirectQueue")
public class RabbitMqDirectConsumer implements ChannelAwareMessageListener {
    /**监听同一将所有的消费者监听写在class上*/

//    @RabbitListener(queues = "sunwul.DirectQueue")

    @RabbitHandler
    public void directMessageConsumer1(String messageMap){
        System.out.println("Direct消费者1接收到的消息: "+messageMap);
    }

//    @RabbitListener(queues = "sunwul.DirectQueue")

    @RabbitHandler
    public void directMessageConsumer2(String messageMap){
        System.out.println("Direct消费者2接收到的消息: "+messageMap);
    }

//    @RabbitListener(queues = "sunwul.DirectQueue")

    @RabbitHandler
    public void directMessageConsumer3(String messageMap){
        System.out.println("Direct消费者3接收到的消息: "+messageMap);
    }

    /**需要手动确认的消息*/
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        long deliveryTag=message.getMessageProperties().getContentLength();
//        long deliveryTag=message.getMessageProperties().getMessageCount();
//        long deliveryTag=message.getMessageProperties().getPriority();
//        long deliveryTag=message.getMessageProperties().getPublishSequenceNumber();
        try {
            //手动的确认模式的投递效率略低于自动,但是可以弥补自动确认模式的不足,更加准确地去记录消息消费情况
            System.out.println(message);
            //手动调用basic.ack/basic.nack/basic.reject ;RabbitMQ收到这些消息后，才认为本次投递成功
            channel.basicAck(deliveryTag, true);
//			channel.basicReject(deliveryTag, true);//为true会重新放回队列
        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }

}
