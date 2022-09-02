package com.tamiresntt.services.services;

import com.tamiresntt.services.domain.UserRegister;
import com.tamiresntt.services.dto.UserDTO;
import com.tamiresntt.services.repository.UserRepository;
import com.tamiresntt.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserRegister> findAll() {
        return userRepository.findAll();
    }

    public UserRegister findById(String id) {
        Optional<UserRegister> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public UserRegister insert(UserRegister obj) {
        return userRepository.insert(obj);
    }

    public UserRegister update(UserRegister obj) {
        UserRegister newObj = findById(obj.getId());
        updateData(newObj, obj);
        return userRepository.save(newObj);
    }

    private void updateData(UserRegister newObj, UserRegister obj) {
        newObj.setUsername(obj.getUsername());
        newObj.setEmail(obj.getEmail());
        newObj.setCpf(obj.getCpf());
        newObj.setAddress(obj.getAddress());
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public UserRegister fromDTO(UserDTO objDto) {
        return new UserRegister();
    }
}
