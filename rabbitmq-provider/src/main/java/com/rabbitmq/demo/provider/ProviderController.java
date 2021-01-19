package com.rabbitmq.demo.provider;

import com.rabbitmq.demo.config.RabbitmqConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
        rabbitTemplate1.convertAndSend(RabbitmqConfig.YAOJIEEXCHANGE,"yaojie",message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                return message;
            }
        });
        rabbitTemplate2.convertAndSend(RabbitmqConfig.YAOJIEEXCHANGE, "yaojie", message,new CorrelationData(UUID.randomUUID().toString()));
        return message;
    }


}
