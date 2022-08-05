package com.tamiresntt.services.dto;

import com.tamiresntt.services.domain.User;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private String id;
    private String name;
    private String email;
    private String country;

    public UserDTO() {
    }

    public UserDTO(@NotNull User obj) {
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
        country = obj.getCountry();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
