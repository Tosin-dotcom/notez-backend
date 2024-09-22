package com.tosin.notez.authentication;


import com.tosin.notez.authentication.dto.LoginDto;
import com.tosin.notez.authentication.dto.LoginResponse;
import com.tosin.notez.exception.NotezException;
import com.tosin.notez.security.service.JwtService;
import com.tosin.notez.user.UserService;
import com.tosin.notez.user.dto.Role;
import com.tosin.notez.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserDto registerUser(UserDto userDto) {

        userDto.setRole(Role.USER);
        return userService.createNewUser(userDto);
    }

    public LoginResponse loginUser(LoginDto loginDto) {

        UserDto userDto = userService.getUserByEmail(loginDto.getEmail())
                .orElseThrow(() -> new NotezException("Invalid login details", HttpStatus.FORBIDDEN));

        boolean matches = passwordEncoder.matches(loginDto.getPassword(), userDto.getPassword());

        if (!matches) {
            throw new NotezException("Invalid login details", HttpStatus.FORBIDDEN);
        }

        userDto.setPassword(null);
        return LoginResponse
                .builder()
                .user(userDto)
                .token(jwtService.generateToken(userDto))
                .build();

    }



}
