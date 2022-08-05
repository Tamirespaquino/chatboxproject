package com.tamiresntt.consumer.service;

import com.tamiresntt.consumer.domain.Message;
import com.tamiresntt.consumer.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    public Message saveMsg(Message obj) {
        return repository.save(obj);
    }

}
