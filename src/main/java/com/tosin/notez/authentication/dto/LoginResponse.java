package com.tosin.notez.authentication.dto;


import com.tosin.notez.user.dto.UserDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private UserDto user;
    private String token;

}
