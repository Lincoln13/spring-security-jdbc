package com.example.authdb.service;

import com.example.authdb.model.Authentication;

public interface RegisterService {
    boolean isAnExistingEmail(String email);
    Authentication saveAuthentication(Authentication auth);
}
