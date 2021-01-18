package com.rabbitmq.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitmqConfig {
    //队列名称
    public  static final  String  YAOJIEQUEUE = "yaojie_queue";

    //交换机名称
    public static final  String  YAOJIEEXCHANGE = "yaojie_exchange";

    //初始化队列
    @Bean("yaojiequeue")
    public Queue getQueue(RabbitAdmin rabbitAdmin){
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

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


    @Bean("yaojierabbit1")
    public RabbitTemplate getRabbitTemplate1(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(new SerializerMessageConverter());
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                if(b){
                    System.out.println("yaojierabbit1发送成功");
                }else{
                    System.out.println("yaojierabbit1发送失败");
                }
            }
        });
        return rabbitTemplate;
    }
    @Bean("yaojierabbit2")
    public RabbitTemplate getRabbitTemplate2(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(new SerializerMessageConverter());
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                if(b){
                    System.out.println("yaojierabbit2发送成功");
                }else{
                    System.out.println("yaojierabbit2发送失败");
                }
            }
        });
        return rabbitTemplate;
    }

}
