package com.tamiresntt.consumer.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageDTO implements Serializable {

    private String message;
    private String sender;
    private String receiver;
    private Long createDate;


    public MessageDTO(String message, String sender, String receiver, Long createDate) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.createDate = createDate;
    }

    public Long getCreateDate() {
        return createDate;
    }

}
