package com.personal.youtubeclone.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.personal.youtubeclone.dto.UserDto;
import com.personal.youtubeclone.service.UserRegistrationService;
import com.personal.youtubeclone.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRegistrationService userRegistrationService;
    private final UserService userService;
    
    @GetMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public UserDto register(Authentication authentication){
       Jwt jwt =  (Jwt)authentication.getPrincipal();
       
      
       return userRegistrationService.registerUser(jwt.getTokenValue());
       
       
    }

    @PostMapping("/subscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean subscribeToUser(@PathVariable String userId) {
        userService.subscribedToUser(userId);
        return true;
    }
    @PostMapping("/unsubscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean unsubscribeToUser(@PathVariable String userId) {
        userService.unsubscribedToUser(userId);
        return true;
    }
    @GetMapping("/history")
    @ResponseStatus(HttpStatus.OK)
    public Set<String> userHistory() {
        
        return userService.userHistory();
    }
}
