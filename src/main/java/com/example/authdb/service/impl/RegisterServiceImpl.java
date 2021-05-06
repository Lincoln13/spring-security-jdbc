package com.example.authdb.service.impl;

import com.example.authdb.dao.AuthenticationDAO;
import com.example.authdb.model.Authentication;
import com.example.authdb.model.Authority;
import com.example.authdb.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private AuthenticationDAO authenticationDAO;

    @Override
    public boolean isAnExistingEmail(String email) {
        return authenticationDAO.findByUsername(email) != null;
    }

    @Override
    public Authentication saveAuthentication(Authentication auth) {
        Authentication authentication = new Authentication(auth);
        authentication.setAuthorityList(getAuthorityList(auth.getAuthorityList(), authentication));
        return authenticationDAO.saveAndFlush(authentication);
    }

    private List<Authority> getAuthorityList(List<Authority> authorityList, Authentication authentication) {
        List<Authority> authorities = new ArrayList<>();
        authorityList.stream().map(Authority::new).forEach(authority -> {
            authority.setAuthentication(authentication);
            authorities.add(authority);
        });
        return authorities;
    }
}
