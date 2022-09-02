package com.tamiresntt.services.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private String id;
    private String username;
    private String password;
    private String email;
    private String cpf;
    private String address;

}
