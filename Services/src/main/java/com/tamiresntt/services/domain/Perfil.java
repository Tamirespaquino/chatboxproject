package com.tamiresntt.services.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

// Esta classe implementa a interface GrantedAuthority
// representando as permissoes concedidas para um user

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "perfil")
public class Perfil implements GrantedAuthority {

    @Id
    private String id;
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }

}
