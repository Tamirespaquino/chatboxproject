package com.tamiresntt.services.repository;

import com.tamiresntt.services.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
