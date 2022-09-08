package com.tamiresntt.consumer.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Message {

    @Id
    private String id;
    private String message;
    private String sender;
    private String receiver;
    private Date create_date;

}