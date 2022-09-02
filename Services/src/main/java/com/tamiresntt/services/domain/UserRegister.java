package com.tamiresntt.services.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "user")
public class UserRegister implements Serializable {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String cpf;
    private String address;
}
