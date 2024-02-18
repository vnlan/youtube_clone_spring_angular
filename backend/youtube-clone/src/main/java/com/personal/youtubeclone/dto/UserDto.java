package com.personal.youtubeclone.dto;


import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String avatar;
    private Integer subCount;
}
