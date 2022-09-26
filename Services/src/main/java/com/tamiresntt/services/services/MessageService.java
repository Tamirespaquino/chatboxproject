package com.tamiresntt.services.services;

import com.tamiresntt.services.domain.Message;
import com.tamiresntt.services.dto.MessageDTO;
import com.tamiresntt.services.exception.ObjectNotFoundException;
import com.tamiresntt.services.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<MessageDTO> findByFilter(String id, String username, LocalDateTime beginDate, LocalDateTime endDate) {

        if (username == null || beginDate == null || endDate == null) {

            var msg = msgRepository.findById(id);
            var listMessage = new ArrayList<MessageDTO>();
            listMessage.add(MessageDTO.converter(msg.get().getMessage(), msg.get().getSender(), msg.get().getReceiver(), msg.get().getCreateDate()));

            return listMessage;
        }

        List<Message> obj = msgRepository.findByFilter(id, username, beginDate, endDate);

        if (obj.isEmpty())
            throw new ObjectNotFoundException("Mensagem nao encontrada");

        return obj.stream().map(MessageDTO::new).collect(Collectors.toList());
    }
}
