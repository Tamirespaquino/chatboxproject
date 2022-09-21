package com.tamiresntt.consumer.service;

import com.tamiresntt.consumer.domain.Message;
import com.tamiresntt.consumer.dto.MessageDTO;
import com.tamiresntt.consumer.repository.MessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;
    @Autowired
    private MessageRepository repository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Message saveMsg(Message obj) {
        return repository.save(obj);
    }

    public void sendToDlq(MessageDTO msg) {

        var message = Message.create(msg.getMessage(), msg.getSender(), msg.getReceiver(), msg.getCreateDate());

        rabbitTemplate.convertAndSend("user.queue.dlq", message);
    }

}
