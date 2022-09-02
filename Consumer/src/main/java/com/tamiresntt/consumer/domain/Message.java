package com.tamiresntt.consumer.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Message {

    @Id
    private String id;
    private String message;
    private String username;
    private String password;
    private Date create_date;

}