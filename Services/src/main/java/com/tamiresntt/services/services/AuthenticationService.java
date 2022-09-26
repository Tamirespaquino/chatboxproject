package com.tamiresntt.services.services;

import com.tamiresntt.services.domain.UserRegister;
import com.tamiresntt.services.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRegister optional = repository.findByUsername(username);

        if(optional != null) {
            return optional;
        }

        throw new UsernameNotFoundException("User not found");
    }
}
