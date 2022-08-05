package com.tamiresntt.consumer.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Message {

    @Id
    private String id;
    private String name;
    private String message;

}