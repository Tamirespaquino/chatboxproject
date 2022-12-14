package com.tamiresntt.services.repository;

import com.tamiresntt.services.domain.UserRegister;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserRegister, String> {

    UserRegister findByUsername(String username);
    Optional<UserRegister> findById(String id);
    UserRegister save(UserRegister user);
}
