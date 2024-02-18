package com.personal.youtubeclone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.personal.youtubeclone.model.Video;

@Repository
public interface VideoRepository extends MongoRepository<Video, String>{
    
}
