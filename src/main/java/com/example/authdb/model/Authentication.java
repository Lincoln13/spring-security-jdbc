package com.example.authdb.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Authentication", schema = "public")
public class Authentication {

    @Id
    @Column(name = "USER_ID")
    @SequenceGenerator(name = "SEQ", sequenceName = "hibernate_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    private Long userId;

    @Column(name = "USER_NAME")
    private String userName;

    private String password;

    private Boolean disabled;

    @OneToMany(mappedBy = "authentication", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Authority> authorityList;

    public Authentication() {
    }

    public Authentication(Authentication auth) {
        this.userId = auth.getUserId();
        this.userName = auth.getUserName();
        this.password = new BCryptPasswordEncoder().encode(auth.getPassword());
        this.disabled = Boolean.FALSE;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public List<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }
}
