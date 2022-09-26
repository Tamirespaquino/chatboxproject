package com.tamiresntt.producer.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageDTO implements Serializable {

    private String message;
    private String sender;
    private String receiver;
    public Status status;
    private String createDate;

    public String getCreateDate() {
        return createDate;
    }
}
