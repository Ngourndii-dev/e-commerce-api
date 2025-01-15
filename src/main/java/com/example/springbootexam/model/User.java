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
    public User(int id, String username, String email, String occupation, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.occupation = occupation;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
