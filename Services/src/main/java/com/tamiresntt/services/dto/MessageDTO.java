package com.tamiresntt.services.dto;

import com.tamiresntt.services.domain.Message;
import com.tamiresntt.services.utils.DateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO implements Serializable {

    private String message;
    private String sender;
    private String receiver;
    private String createDate;

    public MessageDTO(String message, String sender, String receiver, String createDate) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.createDate = createDate;
    }

    public MessageDTO(Message message) {
        this.message = message.getMessage();
        this.sender = message.getSender();
        this.receiver = message.getReceiver();
        this.createDate = DateConverter.toLocalDateTime(message.getCreateDate());
    }

    public static MessageDTO converter(String message, String sender, String receiver, String createDate) {

        return new MessageDTO(message, sender, receiver, createDate);

    }
}

