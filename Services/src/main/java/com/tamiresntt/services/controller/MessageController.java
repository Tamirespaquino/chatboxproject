package com.tamiresntt.services.controller;

import com.tamiresntt.services.dto.MessageDTO;
import com.tamiresntt.services.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

import static com.tamiresntt.services.utils.DateConverter.toLocalDate;

@RestController
@RequestMapping(value = "/messages")
public class MessageController implements Serializable {

    @Autowired
    private MessageService msgService;

    /*@GetMapping
    public ResponseEntity<List<MessageDTO>> find() {
        List<Message> list = msgService.findAll();
        List<MessageDTO> listDto = list.stream().map(x -> new MessageDTO()).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }*/

    @GetMapping
    public ResponseEntity<List<MessageDTO>> findMessage(
            @RequestHeader(value = "username", required = false) String username,
            @RequestHeader(value = "id", required = false) String id,
            @RequestHeader(value = "begin_create_date", required = false) String beginDate,
            @RequestHeader(value = "end_create_date", required = false) String endDate) {

        var initialDate = toLocalDate(beginDate);
        var finalDate = toLocalDate(endDate);

        List<MessageDTO> obj = msgService.findByFilter(id, username, initialDate, finalDate);

        return ResponseEntity.ok().body(obj);
    }

}