package com.tamiresntt.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDTO implements Serializable {

    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String cpf;
    private String address;

}
