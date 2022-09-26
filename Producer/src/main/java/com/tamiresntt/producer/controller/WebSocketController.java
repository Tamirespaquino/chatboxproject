package com.tamiresntt.producer.controller;

import com.tamiresntt.producer.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping(value = "/message")
    @SendTo(value = "/chatroom/public")
    public MessageDTO receiveMessage(@Payload MessageDTO message) throws InterruptedException {
        return message;
    }

    @MessageMapping(value="/private-message")
    public MessageDTO privateMessage(@Payload MessageDTO message) throws InterruptedException {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiver(), "/private", message);
        return message;
    }
}
