package com.rabbitmq.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @RabbitListener(queues = "yaojie_queue")
    public void receive(String message){
        System.out.println(message);
    }
}
