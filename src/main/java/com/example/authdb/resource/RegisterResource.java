package com.example.authdb.resource;

import com.example.authdb.model.Authentication;
import com.example.authdb.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/register")
public class RegisterResource {

    @Autowired
    private RegisterService service;

    @PostMapping
    public ResponseEntity<?> registerAnUser(@RequestBody Authentication auth) {
        if (service.isAnExistingEmail(auth.getUserName())) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        return ResponseEntity.ok(service.saveAuthentication(auth));
    }
}
