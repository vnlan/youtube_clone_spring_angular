package com.personal.youtubeclone.service;

import java.util.Set;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.personal.youtubeclone.model.User;
import com.personal.youtubeclone.model.Video;
import com.personal.youtubeclone.repository.UserRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getCurrentUser(){
   
        String sub = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaim("sub");
        return userRepository.findBySub(sub).orElseThrow(() -> new IllegalArgumentException("Cannot find user with sub - " + sub));
    } 


    public boolean ifLikedVideo(String videoId){

        return getCurrentUser().getLikedVideos().stream().anyMatch(id -> id.equals(videoId));
    }

    
    public String testString(String videoId){
       
        return getCurrentUser().toString();
    }

     public boolean ifDislikedVideo(String videoId){

        return getCurrentUser().getDislikedVideos().stream().anyMatch(id -> id.equals(videoId));
    }
    public void addToLikedVideos(String videoId) {
        User currentUser =  getCurrentUser();
        currentUser.addToLikedVideos(videoId);
        userRepository.save(currentUser);
    }

    public void removeFromLikedVideos(String videoId) {
        User currentUser =  getCurrentUser();
        currentUser.removeFromLikedVideos(videoId);
        userRepository.save(currentUser);
    }

    public void addToDislikedVideos(String videoId) {
        User currentUser =  getCurrentUser();
        currentUser.addToDislikedVideos(videoId);
        userRepository.save(currentUser);
    }
    public void removeFromDislikedVideos(String videoId) {
       
        User currentUser =  getCurrentUser();
        currentUser.removeFromDislikedVideos(videoId);
        userRepository.save(currentUser);
    }


    public void addVideoToHistory(String videoId) {
        User currentUser =  getCurrentUser();
        currentUser.addVideoToHistory(videoId);
        userRepository.save(currentUser);
    }


    public void subscribedToUser(String userId) {
        User currentUser =  getCurrentUser();
        User userHasSubscribed = userRepository.findById(userId)
                                            .orElseThrow(() -> new IllegalArgumentException("Invalid User - " + userId));
        
        currentUser.addToSubscribedToUsers(userId);
        userHasSubscribed.addToSubscribers(currentUser.getId());

        userRepository.save(currentUser);
        userRepository.save(userHasSubscribed);

    }

    public void unsubscribedToUser(String userId) {
        User currentUser =  getCurrentUser();
        User userHasUnsubscribed = userRepository.findById(userId)
                                            .orElseThrow(() -> new IllegalArgumentException("Invalid User - " + userId));
        
        currentUser.removeFromSubscribedToUsers(userId);
        userHasUnsubscribed.removeFromSubscribers(currentUser.getId());

        userRepository.save(currentUser);
        userRepository.save(userHasUnsubscribed);

    }


    public Set<String> userHistory() {
        User currentUser =  getCurrentUser();
        return currentUser.getVideoHistory();
    }
}
