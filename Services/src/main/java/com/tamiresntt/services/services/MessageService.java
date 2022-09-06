package com.tamiresntt.services.services;

import com.tamiresntt.services.domain.Message;
import com.tamiresntt.services.repository.MessageRepository;
import com.tamiresntt.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository msgRepository;
    public List<Message> findAll() {
        return msgRepository.findAll();
    }
    public Message findById(String id) {
        Optional<Message> obj = msgRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Mensagem nao encontrada"));
    }

}
