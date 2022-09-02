package com.tamiresntt.services.repository;

import com.tamiresntt.services.domain.UserRegister;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserRegister, String> {
}
