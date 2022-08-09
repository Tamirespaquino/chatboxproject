package com.tamiresntt.services.controller;

import com.tamiresntt.services.domain.Message;
import com.tamiresntt.services.dto.MessageDTO;
import com.tamiresntt.services.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
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
        List<MessageDTO> listDto = list.stream().map(x -> new MessageDTO(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MessageDTO> findById(@PathVariable String id) {
        Message obj = msgService.findById(id);
        return ResponseEntity.ok().body(new MessageDTO(obj));
    }

}
