package com.personal.youtubeclone.model;


import java.util.HashSet;
import java.util.LinkedHashSet;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailAddress;
    private String avatar;
    private String sub;
    private AtomicInteger subCount = new AtomicInteger(0);
    private Set<String> subscribedToUsers = new HashSet<>();
    private Set<String> subscribers = new HashSet<>();
    private Set<String> videoHistory = new LinkedHashSet<>();
    private Set<String> likedVideos = new HashSet<>();
    private Set<String> dislikedVideos = new HashSet<>();

    public void addToLikedVideos(String videoId) {
        this.likedVideos.add(videoId);
    }
    public void addToDislikedVideos(String videoId) {
        this.dislikedVideos.add(videoId);

    }

    public void removeFromLikedVideos(String videoId) {
        this.likedVideos.remove(videoId);
    }

    public void removeFromDislikedVideos(String videoId) {
        this.dislikedVideos.remove(videoId);
    }

    public void addVideoToHistory(String videoId) {
        this.videoHistory.add(videoId);
    }
    public void addToSubscribedToUsers(String userId) {
      this.subscribedToUsers.add(userId);
    }
    public void addToSubscribers(String userId) {
        this.subscribers.add(userId);
    }
    
    public void removeFromSubscribedToUsers(String userId) {
        this.subscribedToUsers.remove(userId);
    }
    public void removeFromSubscribers(String userId) {
          this.subscribers.remove(userId);
    }

    public Integer getSubCount() {
        return this.subCount.get();
    }

    public void increaseSubCount(){
        this.subCount.incrementAndGet(); 
    }

    public void decreaseSubCount(){
        this.subCount.decrementAndGet(); 
    }
  
}
