package com.tosin.notez.authentication;


import com.tosin.notez.authentication.dto.LoginDto;
import com.tosin.notez.authentication.dto.LoginResponse;
import com.tosin.notez.model.Request;
import com.tosin.notez.model.Response;
import com.tosin.notez.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<Response<UserDto>> register(@RequestBody Request<UserDto> request) {

        UserDto registerUser = authenticationService.registerUser(request.getBody());
        Response<UserDto> response = Response.<UserDto>builder()
                .body(registerUser)
                .status(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponse>> login(@RequestBody Request<LoginDto> request) {

        LoginResponse loginResponse = authenticationService.loginUser(request.getBody());
        Response<LoginResponse> response = Response.<LoginResponse>builder()
                .body(loginResponse)
                .status(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/map")
    public ResponseEntity<String> demoController() {

        return new ResponseEntity<>("working", HttpStatus.OK);
    }

}
