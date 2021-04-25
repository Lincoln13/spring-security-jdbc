package com.example.authdb.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class Users {

    @GetMapping
    public ResponseEntity<?> users() {
        return ResponseEntity.ok("Welcome users and Admins");
    }
}
