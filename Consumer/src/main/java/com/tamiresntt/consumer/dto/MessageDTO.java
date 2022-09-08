package com.tamiresntt.consumer.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MessageDTO implements Serializable {

    private String id;
    private String message;
    private String sender;
    private String receiver;
    private Date create_date;

}
