package com.tamiresntt.services.repository;

import com.tamiresntt.services.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    @Query("{'id':?0,'username':?1,'beginDate':?2,'endDate':?3}")
    public List<Message> findByFilter(String id, String username, LocalDateTime beginDate, LocalDateTime endDate);

}
