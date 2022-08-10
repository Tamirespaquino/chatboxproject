package com.tamiresntt.services.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private String id;
    private String userName;
    private Integer password;
    private String email;
    private Integer cpf;
    private String address;

}
