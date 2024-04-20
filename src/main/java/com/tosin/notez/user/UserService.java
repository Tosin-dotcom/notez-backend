package com.tosin.notez.user;


import com.tosin.notez.security.service.JwtService;
import com.tosin.notez.security.service.TokenDetail;
import com.tosin.notez.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserDto createNewUser(UserDto userDto) {

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userDto);
    }

    public Optional<UserDto> getUserByEmail(String email) {

        return userRepository.findUserByEmail(email);
    }

    public UserDto getUserDetails() {

        TokenDetail tokenDetail = jwtService.getUserDetails();
        return UserDto.builder()
                .id(tokenDetail.getId())
                .email(tokenDetail.getEmail())
                .firstName(tokenDetail.getFirstName())
                .lastName(tokenDetail.getLastName())
                .role(tokenDetail.getRole())
                .build();
    }

}
