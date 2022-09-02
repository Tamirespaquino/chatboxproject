package com.tamiresntt.services.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class UserLogin {

    private String username;
    private String password;
}
