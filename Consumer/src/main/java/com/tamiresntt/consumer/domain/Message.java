package com.tamiresntt.consumer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    private String id;
    private String message;
    private String sender;
    private String receiver;
    private String createDate;

    public Message(String message, String sender, String receiver, String createDate) {

        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.createDate = createDate;
    }

    public static Message create(String message, String sender, String receiver, String createDate) {

        return new Message(message, sender, receiver, createDate);
    }
}