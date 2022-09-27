package com.tamiresntt.services.repository;

import com.tamiresntt.services.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    @Query("{$and : [ { $or:[ $where: '?0 == null' },{'id':?0 } ]},{ $or:[ $where: '?1 == null' },{'username':?1 } ]}    ]}")
    public List<Message> findByFilter(String username, LocalDate beginDate, LocalDate endDate);

}
