package com.tamiresntt.producer.controller;

import com.tamiresntt.producer.dto.MessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping(value = "/message")
    @SendTo(value = "/chat/message")
    public MessageDTO realtimeMsg(MessageDTO msg) throws InterruptedException {
        System.out.println("o que retornou daqui");
        return msg;
    }
}
