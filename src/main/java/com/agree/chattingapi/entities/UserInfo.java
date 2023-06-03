package com.agree.chattingapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo extends CommonEntity{

    @Id
    @Column(name = "user_id", nullable = false, length = 15)
    private String id;

    @Column(name = "password", nullable = false, length = 255)
    private String pw;

    @Column(name = "name", nullable = false, length = 12)
    private String name;

    @Column(name = "birth", nullable = false, length = 6)
    private String birth;

    @Column(name = "push_key")
    private String pushKey;

    public UserInfo(String id, String pw, String name, String birth) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.birth = birth;
    }

    public UserInfo() {}

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }
}
