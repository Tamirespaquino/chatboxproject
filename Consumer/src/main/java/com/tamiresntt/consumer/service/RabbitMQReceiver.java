package com.tamiresntt.consumer.service;

import com.tamiresntt.consumer.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiver implements RabbitListenerConfigurer {

    @Autowired
    MessageService messageService;

    private static final Logger logger = LoggerFactory.getLogger(
            RabbitMQReceiver.class);

    @RabbitListener(queues = {"${spring.rabbitmq.queue}"})
    public void receivedMessage(@Payload Message message) {
        logger.info("Message received is " + message);
        messageService.saveMsg(message);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

}
