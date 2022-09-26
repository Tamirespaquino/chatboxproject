package com.tamiresntt.producer.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    private LocalDateTime createDate;

    public LocalDateTime getCreateDate() {
        return createDate;
    }
}
