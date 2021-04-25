package com.example.authdb.dao;

import com.example.authdb.model.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationDAO extends JpaRepository<Authentication, Long> {

    @Query("from Authentication auth where " +
            "auth.userName = :userName and " +
            "auth.disabled = false")
    public Authentication findByUsername(@Param("userName") String userName);
}
