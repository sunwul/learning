package com.sunwul.mq.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*****
 * @author: Sunwul
 * @date: 2020/3/1 22:50
 * PS: Topic Exchange (主题交换机)
 */
@Configuration
public class TopicRabbitConfig {

    /**定义队列 1*/
    @Bean
    public Queue topicQueue1(){
        return new Queue("sunwul.TopicQueue1",true);
    }

    /**队列 2*/
    @Bean
    public Queue topicQueue2(){
        return new Queue("sunwul.TopicQueue2",true);
    }

    /**定义Topic交换机*/
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("sunwul.TopicExchange");
    }

    /**将队列1与交换机绑定 设定路由键 sunwul.TopicRouting*/
    @Bean
    Binding bindingTopic1(){
        return BindingBuilder.bind(topicQueue1())
                .to(topicExchange())
                .with("sunwul.TopicRouting");
    }

    /**将队列2与交换机绑定 设定路由键 sunwul.#
     * 当一个队列的绑定键为 "#"（井号） 的时候，这个队列将会无视消息的路由键，接收所有的消息。
     * 例: 有两个队列(q1,q2)分别绑定路由键sunwul.a和sunwul.#
     *     有两条消息分别携带sunwul.a和sunwul.b路由键,则q1会收到路由键为sunwul.a的消息
     *     q2因为使用了#号通配符,会收到路由键为sunwul.XXX的所有消息
     * 当 * (星号) 和 # (井号) 这两个特殊字符都未在绑定键中出现的时候
     * 此时主题交换机就拥有直连交换机的行为
     * 绑定键带 * 的问题:
     * 队列Q1 绑定键为 *.TT.*          队列Q2绑定键为  TT.#
     * 如果一条消息携带的路由键为 A.TT.B 那么队列Q1将会收到；
     * 如果一条消息携带的路由键为TT.AA.BB 那么队列Q2将会收到；
     *  * 号代表了一个必须出现的字符
     * */
    @Bean
    Binding bindingTopic2(){
        return BindingBuilder.bind(topicQueue2())
                .to(topicExchange())
                .with("sunwul.#");
    }
}
