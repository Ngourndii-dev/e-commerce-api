package com.example.springbootexam.model;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Order{
    private int id;
    private Date orderDate;
    private Boolean status;
    private int quantity;
    private float totalPrice;
    private Client client;
    private Product product;
    public Order(){}
    public Order(Date orderDate, Boolean status, int quantity, float totalPrice, Client client, Product product) {
        this.orderDate = orderDate;
        this.status = status;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.client = client;
        this.product = product;
    }

}























