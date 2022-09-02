package com.tamiresntt.producer.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    private String id;
    private String message;
    private String username;
    private String password;
    private Date create_date;
}
