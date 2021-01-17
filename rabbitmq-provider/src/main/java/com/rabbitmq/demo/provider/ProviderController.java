package com.rabbitmq.demo.provider;

import com.rabbitmq.demo.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping(value = "/provider/message")
    public String provider(String message){
        rabbitTemplate.convertAndSend(RabbitmqConfig.YAOJIEEXCHANGE,"yaojie",message);
        return message;
    }
}
