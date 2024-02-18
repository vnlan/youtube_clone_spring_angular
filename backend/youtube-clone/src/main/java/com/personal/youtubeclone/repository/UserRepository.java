package com.personal.youtubeclone.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.personal.youtubeclone.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

    Optional<User> findBySub(String sub);
    
}
