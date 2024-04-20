package com.tosin.notez.authentication;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {


    @GetMapping("demo")
    public ResponseEntity<String> demo() {

        return new ResponseEntity<>("authorised endpoint", HttpStatus.OK);
    }




}
