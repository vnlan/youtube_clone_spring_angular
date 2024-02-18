package com.personal.youtubeclone.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.youtubeclone.dto.UserAuthDto;
import com.personal.youtubeclone.dto.UserDto;
import com.personal.youtubeclone.mapper.UserMapper;
import com.personal.youtubeclone.model.User;
import com.personal.youtubeclone.repository.UserRepository;
import com.personal.youtubeclone.util.UtilString;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {


    private String userInfoEndpoint = UtilString.auth0_userInfoEndpoint;

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserDto registerUser(String tokenValue){
       HttpRequest httpRequest = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create(userInfoEndpoint))
        .setHeader("Authorization", String.format("Bearer %s", tokenValue))
        .build();

        HttpClient httpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .build();

        try {
            HttpResponse<String> responseString = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String body = responseString.body();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            UserAuthDto userAuthDto =  objectMapper.readValue(body, UserAuthDto.class);

            Optional<User> userBySub = userRepository.findBySub(userAuthDto.getSub());
            
            if (userBySub.isPresent()) {
                return userMapper.mapToDto(userBySub.get());
            }else{
                User user = new User();
                user.setFirstName(userAuthDto.getGivenName());
                user.setLastName(userAuthDto.getFamilyName());
                user.setFullName(userAuthDto.getName());
                user.setEmailAddress(userAuthDto.getEmail());
                user.setSub(userAuthDto.getSub());
                user.setAvatar(userAuthDto.getPicture());
                userRepository.save(user);
                
                return userMapper.mapToDto(user);
            }

            


        } catch (Exception ex) {
            throw new RuntimeException("Error While Registering User", ex);
        }
    }
}
