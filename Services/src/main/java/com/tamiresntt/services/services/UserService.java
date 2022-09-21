package com.tamiresntt.services.services;

import com.tamiresntt.services.domain.UserRegister;
import com.tamiresntt.services.dto.UserRegisterDTO;
import com.tamiresntt.services.exception.ObjectNotFoundException;
import com.tamiresntt.services.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Validator validator;

    public List<UserRegister> findAll() {
        return userRepository.findAll();
    }

    public UserRegister findById(String id) {
        Optional<UserRegister> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public UserRegister insert(UserRegisterDTO request) {

        var result = validator.validate(request); // valida as anotações antes de fazer o resto do codigo
        if (!result.isEmpty()) {
            throw new ConstraintViolationException(result);
        }

        // validar o endereço

        UserRegister obj = new UserRegister();
        fromDTO(request, obj);

        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
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

    public UserRegister fromDTO(UserRegisterDTO objDto, UserRegister obj) {
        obj.setAddress(objDto.getAddress());
        obj.setCpf(objDto.getCpf());
        obj.setEmail(objDto.getEmail());
        obj.setUsername(objDto.getUsername());
        obj.setPassword(objDto.getPassword());

        return obj;
    }
}
