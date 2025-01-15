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
public class Client {
    private int id;
    private String clientName;
    private String phoneNumber;
    private String email;

    public Client(int id, String clientName, String phoneNumber, String email) {
        this.id = id;
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Client(String clientName, String phoneNumber, String email) {
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public Client(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
