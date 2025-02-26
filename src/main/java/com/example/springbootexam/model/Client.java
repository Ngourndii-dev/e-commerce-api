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

    public Client(String clientName, String phoneNumber, String email) {
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public Client(){}
}
