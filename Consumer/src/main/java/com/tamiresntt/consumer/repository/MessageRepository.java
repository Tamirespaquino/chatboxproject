package com.tamiresntt.consumer.repository;

import com.tamiresntt.consumer.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
