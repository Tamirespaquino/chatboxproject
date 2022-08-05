package com.tamiresntt.producer.controller;

import com.tamiresntt.producer.domain.Message;
import com.tamiresntt.producer.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer/send-message")
public class ProducerController {

    private RabbitMQSender rabbitMQSender;

    @Autowired
    public ProducerController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @Value("${app.message}")
    private String message;

    // Envia mensagem para o RabbitMQ
    @PostMapping("message")
    public String insertMessage(@RequestBody Message msg) {
        rabbitMQSender.send(msg);
        return message;
    }
}
