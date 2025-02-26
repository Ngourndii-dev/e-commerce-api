package com.example.springbootexam.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Cart {
    private int id;
    private int reference;
    private String typeCart;
    private Client client;

    public Cart(){}
    public Cart(int reference, String typeCart, Client client) {
        this.reference = reference;
        this.typeCart = typeCart;
        this.client = client;
    }
}
