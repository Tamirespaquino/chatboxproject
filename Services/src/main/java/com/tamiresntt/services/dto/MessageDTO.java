package com.tamiresntt.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MessageDTO implements Serializable {

    private String id;
    private String message;
    private String username;
    private String password;
    private Date createDate;

    public MessageDTO() {

    }
}

