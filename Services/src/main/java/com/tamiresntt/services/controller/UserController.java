package com.tamiresntt.services.controller;

import com.tamiresntt.services.domain.UserRegister;
import com.tamiresntt.services.dto.UserRegisterDTO;
import com.tamiresntt.services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController implements Serializable {

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<UserRegisterDTO>> findAll() {
        List<UserRegister> list = userService.findAll();
        List<UserRegisterDTO> listDto = list.stream().map(x -> new UserRegisterDTO()).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserRegisterDTO> findById(@PathVariable String id) {
        UserRegister obj = userService.findById(id);
        return ResponseEntity.ok().body(new UserRegisterDTO());
    }

    // user register
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserRegisterDTO objDto) {
        UserRegister obj = new UserRegister();
        userService.fromDTO(objDto, obj);
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserRegisterDTO objDto, @PathVariable String id) {
        UserRegister obj = new UserRegister();
        userService.fromDTO(objDto, obj);
        obj.setId(id);
        obj = userService.update(obj);
        return ResponseEntity.noContent().build();
    }
}
