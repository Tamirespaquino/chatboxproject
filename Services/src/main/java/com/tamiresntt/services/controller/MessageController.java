package com.tamiresntt.services.controller;

import com.tamiresntt.services.domain.Message;
import com.tamiresntt.services.dto.MessageDTO;
import com.tamiresntt.services.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/messages")
public class MessageController implements Serializable {

    @Autowired
    private MessageService msgService;

    @GetMapping
    public ResponseEntity<List<MessageDTO>> findAll() {
        List<Message> list = msgService.findAll();
        List<MessageDTO> listDto = list.stream().map(x -> new MessageDTO()).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping() // passar no header do axios do front
    public ResponseEntity<List<MessageDTO>> findById(
            @RequestHeader(value = "username") String user,
            @RequestHeader(value = "id") String id,
            @RequestHeader(value = "begin_create_date") LocalDateTime beginDate,
            @RequestHeader(value = "end_create_date") LocalDateTime endDate) {

        // colocar um if ou algo assim para exigir pelo menos um negocio
        Message obj = msgService.findById(id); // criar outro método chamado findByFilter e passar todos os parametros. Sempre considerar o id, mas os outros nao
        return ResponseEntity.ok()
                .body(new MessageDTO()); // retornar uma lista de mensagens no service
    }

}