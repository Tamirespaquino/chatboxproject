package com.tamiresntt.consumer.dto;

import com.tamiresntt.consumer.domain.Message;

import java.io.Serializable;

public class MessageDTO implements Serializable {

    private String id;
    private String name;
    private String message;

    public MessageDTO() {
    }

    public MessageDTO(Message msg) {
        id = msg.getId();
        name = msg.getName();
        message = msg.getMessage();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
