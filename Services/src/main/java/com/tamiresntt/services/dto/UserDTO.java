package com.tamiresntt.services.dto;

import com.tamiresntt.services.domain.UserRegister;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    String id;
    String firstname;
    String lastname;
    String username;
    String email;

    public static UserDTO converter(UserRegister userRegister){
        return new UserDTO(userRegister.getId(), userRegister.getFirstname(), userRegister.getLastname(), userRegister.getUsername(), userRegister.getEmail());
    }
}
