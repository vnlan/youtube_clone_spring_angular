package com.personal.youtubeclone.mapper;

import org.springframework.stereotype.Service;

import com.personal.youtubeclone.dto.UserDto;
import com.personal.youtubeclone.model.User;

@Service
public class UserMapper {
     public UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailAddress(user.getEmailAddress())
                .avatar(user.getAvatar())
                .subCount(user.getSubCount())
                .build();
    }
}
