package com.tamiresntt.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MessageDTO implements Serializable {

    private String message;
    private String sender;
    private String receiver;
    private LocalDateTime createDate;

    public LocalDateTime getCreateDate() {
        return createDate;
    }
}
