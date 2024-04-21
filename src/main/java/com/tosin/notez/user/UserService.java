package com.tosin.notez.user;


import com.tosin.notez.security.service.JwtService;
import com.tosin.notez.security.service.TokenDetail;
import com.tosin.notez.user.dto.Role;
import com.tosin.notez.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Value("${notez.admin-email}")
    public String adminEmail;

    @Value("${notez.admin-password}")
    public String adminPassword;

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

    private boolean userExists(String email) {

        return userRepository.userExists(email);
    }


    public void createAdminUser() {

        if (!userExists(adminEmail)) {
            UserDto userDto = UserDto.builder()
                    .email(adminEmail)
                    .role(Role.ADMIN)
                    .firstName("admin")
                    .lastName("user")
                    .password(adminPassword)
                    .build();
            createNewUser(userDto);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createAdminAfterStartup() {

        createAdminUser();
    }

}
