package com.example.springbootexam.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private int id;
    private String username;
    private String email;
    private String occupation;
    private String password;

    public User(String username, String email, String occupation, String password) {
        this.username = username;
        this.email = email;
        this.occupation = occupation;
        this.password = password;
    }

    public User(){

    }
}
