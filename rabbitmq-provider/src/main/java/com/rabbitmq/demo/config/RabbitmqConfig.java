package com.rabbitmq.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {
    //队列名称
    public  static final  String  YAOJIEQUEUE = "yaojie_queue";

    //交换机名称
    public static final  String  YAOJIEEXCHANGE = "yaojie_exchange";

    //初始化队列
    @Bean("yaojiequeue")
    public Queue getQueue(){
        return QueueBuilder.durable(YAOJIEQUEUE).build();
    }

    //初始化交换机

    @Bean("yaojieexchange")
    public Exchange getExchange(){
        return ExchangeBuilder.topicExchange(YAOJIEEXCHANGE).durable(true).build();
    }

    //绑定

    @Bean
    public Binding binding(@Qualifier("yaojiequeue") Queue yaojiequeue, @Qualifier("yaojieexchange") Exchange yaojieexchange){
        return BindingBuilder.bind(yaojiequeue).to(yaojieexchange).with("yaojie").noargs();
    }
}
