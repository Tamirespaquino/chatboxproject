package com.tamiresntt.producer.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {

    private String id;
    private String message;
    private String sender;
    private String receiver;
    private LocalDateTime createDate;

    private Message (String message, String sender, String receiver, LocalDateTime createDate) {
        this.message = message;
        this.sender = sender;
        this.sender = receiver;
        this.createDate = createDate;
    }

    public static Message create(String message, String sender, String receiver, LocalDateTime createDate) {
        return new Message(message, sender, receiver, createDate);
    }
}
