package com.tosin.notez.authentication;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {


    @GetMapping("test")
    public ResponseEntity<String> demo() {

        return new ResponseEntity<>("Welcome to Notez", HttpStatus.OK);
    }




}
