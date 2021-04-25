package com.example.authdb.security.service;

import com.example.authdb.dao.AuthenticationDAO;
import com.example.authdb.model.Authentication;
import com.example.authdb.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthenticationDAO authenticationDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return new User("Ram", "Password", new ArrayList<>());
        Authentication authentication = authenticationDAO.findByUsername(s);
        Set<GrantedAuthority> grantedAuthorities = getAuthorities(authentication);
        return new User(authentication.getUserName(), authentication.getPassword(), grantedAuthorities);
    }

    private Set<GrantedAuthority> getAuthorities(Authentication authentication) {
        return authentication.getAuthorityList().stream()
                .filter(this::isValid)
                .map(Authority::getRoleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    private boolean isValid(Authority authority) {
        Date date = new Date();
        return (authority.getStartDate()!= null &&
                (authority.getStartDate().equals(date) || authority.getStartDate().before(date))) &&
                (authority.getEndDate() == null || (authority.getEndDate() != null && authority.getEndDate().after(date)));
    }
}
