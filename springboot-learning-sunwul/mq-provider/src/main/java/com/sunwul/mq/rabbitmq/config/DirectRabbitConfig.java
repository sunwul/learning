package com.sunwul.mq.rabbitmq.config;

import org.apache.catalina.User;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConversionException;

import javax.jms.Message;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/*****
 * @author: Sunwul
 * @date: 2020/2/28 14:22
 * PS: direct exchange (直连型交换机)
 */
@Configuration
public class DirectRabbitConfig {

    //消息转换器 rabbitMQ默认用java的方式序列化消息对象,此处引入转换器将消息以json输出*/

//    @Bean
//    public MessageConverter getMessageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }

    //设置消息序列化 01  已验证*/
//    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private void setRabbitTemplate(RabbitTemplate rabbitTemplate){
//        this.rabbitTemplate=rabbitTemplate;
        //设置序列化的方式 json
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//    }

    //设置消息序列化 02 未验证*/
//    @Bean
//    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        return factory;
//    }

    ///**定义队列*/

    @Bean
    public Queue directQueue(){
        //定义队列名  true:是否持久
        return new Queue("sunwul.DirectQueue",true);
    }

    /**定义Direct交换机*/
    @Bean
    DirectExchange directExchange(){
        //定义交换机名字
        return new DirectExchange("sunwul.DirectExchange");
    }

    /**绑定 将队列与交换机进行绑定 设置路由键 DirectRouting*/
    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(directQueue())
                .to(directExchange())
                .with("DirectRouting");
    }

    /**回调测试 交换机不绑定队列*/
    @Bean
    DirectExchange callBackDirectExchange(){
        return new DirectExchange("sunwul.CallbackDirectExchange");
    }

}
