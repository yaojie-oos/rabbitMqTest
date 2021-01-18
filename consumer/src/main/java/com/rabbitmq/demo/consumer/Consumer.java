package com.rabbitmq.demo.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareBatchMessageListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.List;

@Component
public class Consumer implements ChannelAwareMessageListener {

    @Override
    @RabbitListener(queues = "yaojie_queue")
    public void onMessage(Message message, Channel channel)  {
        try {
            System.out.println(new String(message.getBody()));
            //int si = 1/0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        }catch (Exception e){
            try {
                //requeue设置为false 则不发回原来队列，若绑定死刑交换机则路由到死刑交换机
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),true,true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
