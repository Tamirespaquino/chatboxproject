package com.tamiresntt.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MessageDTO implements Serializable {

    private String message;
    private String sender;
    private String receiver;
    private Date create_date;

}
