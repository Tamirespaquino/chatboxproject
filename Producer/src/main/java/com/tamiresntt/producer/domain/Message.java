package com.tamiresntt.producer.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    private String id;
    private String message;
    private String sender;
    private String receiver;
    private Date create_date;

    private Message (String message, String sender, String receiver, Date create_date) {
        this.message = message;
        this.sender = sender;
        this.sender = receiver;
        this.create_date = create_date;
    }

    public static Message create(String message, String sender, String receiver, Date create_date) {
        return new Message(message, sender, receiver, create_date);
    }
}
