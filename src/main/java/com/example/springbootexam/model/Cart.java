package com.example.springbootexam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Cart {
    private int id;
    private int reference;
    private String typeCart;
    private Client client;

    public Cart(int reference, String typeCart, Client client) {
        this.reference = reference;
        this.typeCart = typeCart;
        this.client = client;
    }

    public Cart(int id, int reference, String typeCart, Client client) {
        this.id = id;
        this.reference = reference;
        this.typeCart = typeCart;
        this.client = client;
    }
public Cart(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getTypeCart() {
        return typeCart;
    }

    public void setTypeCart(String typeCart) {
        this.typeCart = typeCart;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
