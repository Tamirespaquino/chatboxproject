package com.tamiresntt.services.controller;

import com.tamiresntt.services.domain.Message;
import com.tamiresntt.services.dto.MessageDTO;
import com.tamiresntt.services.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<MessageDTO> listDto = list.stream().map(x -> new MessageDTO()).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MessageDTO> findById(@PathVariable String id) {
        Message obj = msgService.findById(id);

        @RequestHeader()
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("id", obj.getId());
        responseHeaders.add("username", obj.getUsername());
        responseHeaders.add("begin_create_date", obj.getCreate_date().toString());
        responseHeaders.add("end_create_date", obj.getCreate_date().toString());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new MessageDTO());
    }

}
