package com.sunwul.mq.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*****
 * @author: Sunwul
 * @date: 2020/3/2 14:41
 * PS: Fanout Exchang (扇形交换机)
 */
@Configuration
public class FanoutRabbitConfig {

    /**定义三个队列A,B,C*/
    @Bean
    public Queue fanoutQueueA(){
        return new Queue("sunwul.FanoutA");
    }

    @Bean
    public Queue fanoutQueueB(){
        return new Queue("sunwul.FanoutB");
    }

    @Bean
    public Queue fanoutQueueC(){
        return new Queue("sunwul.FanoutC");
    }

    /**定义交换机*/
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("sunwul.FanoutExchange");
    }

    /**将队列与交换机进行绑定 扇形交换机无需配置路由键,配置了也不生效  没有配置路由键的属性,配置了会报错
     * */
    @Bean
    public Binding bindingFanoutA(){
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutB(){
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutC(){
        return BindingBuilder.bind(fanoutQueueC()).to(fanoutExchange());
    }
}
