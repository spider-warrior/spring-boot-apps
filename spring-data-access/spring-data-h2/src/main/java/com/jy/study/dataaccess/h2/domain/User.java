package com.jy.study.dataaccess.h2.domain;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "study_user")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
            "username='" + username + '\'' +
            "} " + super.toString();
    }
}
