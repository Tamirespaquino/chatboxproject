package com.tamiresntt.producer.service;

import com.tamiresntt.producer.domain.Message;
import com.tamiresntt.producer.dto.MessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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

        var zdt = ZonedDateTime.of(date, ZoneId.systemDefault());
        long dateLong = zdt.toInstant().toEpochMilli();

        var message = Message.create(msg.getMessage(), msg.getSender(), msg.getReceiver(), dateLong);

        rabbitTemplate.convertAndSend(exchange, routingkey, message);
    }
}
