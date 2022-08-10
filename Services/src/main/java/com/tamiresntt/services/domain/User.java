package com.tamiresntt.services.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "user")
public class User implements Serializable {

    @Id
    private String id;
    private String userName;
    private Integer password;
    private String email;
    private Integer cpf;
    private String address;
}
