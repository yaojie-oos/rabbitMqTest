package com.rabbitmq.demo.provider;

import com.rabbitmq.demo.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    @Autowired
    @Qualifier("yaojierabbit1")
    private RabbitTemplate rabbitTemplate1;
    @Autowired
    @Qualifier("yaojierabbit2")
    private RabbitTemplate rabbitTemplate2;

    @GetMapping(value = "/provider/message")
    public String provider(String message){
        rabbitTemplate1.convertAndSend(RabbitmqConfig.YAOJIEEXCHANGE,"yaojie",message);
        rabbitTemplate2.convertAndSend(RabbitmqConfig.YAOJIEEXCHANGE,"yaojie",message);
        return message;
    }
}
