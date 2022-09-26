package com.tamiresntt.producer.service;

import com.tamiresntt.producer.domain.Message;
import com.tamiresntt.producer.dto.MessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RabbitMQSender {

    private RabbitTemplate rabbitTemplate;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void send(MessageDTO msg) {

        LocalDateTime date = LocalDateTime.now();
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        var message = Message.create(msg.getMessage(), msg.getSender(), msg.getReceiver(), date.format(formatter));

        rabbitTemplate.convertAndSend(exchange, routingkey, message);
    }
}
