package com.example.authdb.resource;

import com.example.authdb.dao.AuthenticationDAO;
import com.example.authdb.model.Authentication;
import com.example.authdb.model.Authority;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterResource {

    @Autowired
    private AuthenticationDAO authenticationDAO;

    @PostMapping
    public ResponseEntity<?> registerAnUser(@RequestBody Authentication auth) {
        Authentication authentication = authenticationDAO.findByUsername(auth.getUserName());
        if (authentication != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        authentication = new Authentication(auth);
        List<Authority> authorityList = getAuthorityList(auth.getAuthorityList(), authentication);
        authentication.setAuthorityList(authorityList);
        authentication = authenticationDAO.saveAndFlush(authentication);
        return ResponseEntity.ok(authentication);
    }

    private List<Authority> getAuthorityList(List<Authority> authorityList, Authentication authentication) {
        List<Authority> authorities = new ArrayList<>();
        for(Authority a: authorityList) {
            Authority authority = new Authority(a);
            authority.setAuthentication(authentication);
            authorities.add(authority);
        }
        return authorities;
    }
}
