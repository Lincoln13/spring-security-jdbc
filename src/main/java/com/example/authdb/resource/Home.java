package com.example.authdb.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Home {

    @GetMapping
    public ResponseEntity<?> home(){
        return ResponseEntity.ok("Welcome Home! All are allowed");
    }
}
