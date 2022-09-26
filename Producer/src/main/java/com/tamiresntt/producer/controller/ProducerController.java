package com.tamiresntt.producer.controller;

import com.tamiresntt.producer.dto.MessageDTO;
import com.tamiresntt.producer.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send-message")
public class ProducerController {

    private RabbitMQSender rabbitMQSender;

    @Autowired
    public ProducerController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @Value("${app.message}")
    private String message;

    @PostMapping
    public String insertMessage(@RequestBody MessageDTO msg) {
        System.out.println("mensagem" + msg);
        rabbitMQSender.send(msg);
        return message;
    }
}
