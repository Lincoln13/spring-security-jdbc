package com.example.authdb.resource;

import com.example.authdb.dao.AuthenticationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Home {

    @Autowired
    private AuthenticationDAO dao;

    @GetMapping
    public ResponseEntity<?> home(){
        return ResponseEntity.ok("Welcome Home! All are allowed");
    }
}
