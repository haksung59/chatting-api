package com.agree.chattingapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User extends CommonEntity{

    @Id
    @Column(name = "user_id", length = 15)
    private String userId;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "name", length = 12)
    private String name;

    @Column(name = "birth", length = 6)
    private String birth;

    public User(String userId, String password, String name, String birth) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.birth = birth;
    }

    public User() {}

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

}
