package com.tamiresntt.producer.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {

    private String id;
    private String message;
    private String sender;
    private String receiver;
    private Long createDate;

    private Message (String message, String sender, String receiver, Long createDate) {
        this.message = message;
        this.sender = sender;
        this.sender = receiver;
        this.createDate = createDate;
    }

    public static Message create(String message, String sender, String receiver, Long createDate) {
        return new Message(message, sender, receiver, createDate);
    }
}
