package com.tamiresntt.services.services;

import com.tamiresntt.services.domain.Message;
import com.tamiresntt.services.dto.MessageDTO;
import com.tamiresntt.services.exception.ObjectNotFoundException;
import com.tamiresntt.services.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<MessageDTO> findByFilter(String id, String message, String username, String password, Date createDate) {

        if (message.isEmpty() || username.isEmpty() || password.isEmpty() || createDate == null) {
            Optional<Message> obj = msgRepository.findById(id);
            return List<obj>;
        }


        Optional<Message> obj = msgRepository.find;

        return obj.orElseThrow(() -> new ObjectNotFoundException("Mensagem nao encontrada"));
    }

    // criar outro método chamado findByFilter e passar todos os parametros. Sempre considerar o id, mas os outros nao

}
