package com.tosin.notez.user;


import com.tosin.notez.model.Response;
import com.tosin.notez.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("user-profile")
    public ResponseEntity<Response<UserDto>> getUserProfile() {

        Response<UserDto> response = Response.<UserDto>builder()
                .body(userService.getUserDetails())
                .status(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
