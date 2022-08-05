package com.tamiresntt.services.dto;

import com.tamiresntt.services.domain.Message;

import java.io.Serializable;
import java.util.Date;

public class MessageDTO implements Serializable {

    private String id;
    private String name;
    private String message;
    private Date date;

    public MessageDTO() {
    }

    public MessageDTO(Message msg) {
        id = msg.getId();
        name = msg.getName();
        message = msg.getMessage();
        date = msg.getDate();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

