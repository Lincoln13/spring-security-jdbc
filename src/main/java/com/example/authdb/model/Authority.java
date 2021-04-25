package com.example.authdb.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Authority", schema = "public")
public class Authority {

    @Id
    @Column(name = "Authority_id")
    @SequenceGenerator(name = "SEQ", sequenceName = "AUTHORITY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    private Long authorityId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Authentication.class)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id", nullable = false)
    @JsonBackReference
    private Authentication authentication;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "start_date")
    private Date startDate = new Date();

    @Column(name = "end_date")
    private Date endDate;

    public Authority() {}

    public Authority(Authority a) {
        this.authorityId = a.getAuthorityId();
        this.roleName = a.getRoleName();
        this.startDate = setDateIfNull(a.getStartDate(), 0);
        this.endDate = setDateIfNull(a.getEndDate(), 12);
    }

    private Date setDateIfNull(Date date, int i) {
        if (date == null) {
            return new DateTime().plusMonths(i).toDate();
        }
        return date;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}