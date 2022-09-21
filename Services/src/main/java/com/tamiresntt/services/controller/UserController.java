package com.tamiresntt.services.controller;

import com.tamiresntt.services.domain.UserRegister;
import com.tamiresntt.services.dto.TokenDTO;
import com.tamiresntt.services.dto.UserLoginDTO;
import com.tamiresntt.services.dto.UserRegisterDTO;
import com.tamiresntt.services.services.TokenService;
import com.tamiresntt.services.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController implements Serializable {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;
   // private final PasswordEncoder encoder;

    public UserController(AuthenticationManager authenticationManager, TokenService tokenService, UserService userService, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userService = userService;
       // this.encoder = encoder;
    }

    // register - cadastrar usuario
    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@RequestBody UserRegisterDTO objDto) {

        userService.insert(objDto);

        return ResponseEntity.ok().build();
    }

    // login - login do usuario com autenticacao de senha e cadastro
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Validated UserLoginDTO loginDTO){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            String token = tokenService.generateToken(authentication);

            return ResponseEntity.ok(TokenDTO.builder().type("Bearer").token(token).build());
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }

    // findAll - lista todos usuarios
    @GetMapping
    public ResponseEntity<List<UserRegisterDTO>> findAll() {
        List<UserRegister> list = userService.findAll();
        List<UserRegisterDTO> listDto = list.stream().map(x -> new UserRegisterDTO()).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    // findById - encontra por Id (retirar?)
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserRegisterDTO> findById(@PathVariable String id) {
        UserRegister obj = userService.findById(id);
        return ResponseEntity.ok().body(new UserRegisterDTO());
    }

    // delete - deleta usuario do BD
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // update - altera informacoes do usuario
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserRegisterDTO objDto, @PathVariable String id) {
        UserRegister obj = new UserRegister();
        userService.fromDTO(objDto, obj);
        obj.setId(id);
        obj = userService.update(obj);
        return ResponseEntity.noContent().build();
    }
}
