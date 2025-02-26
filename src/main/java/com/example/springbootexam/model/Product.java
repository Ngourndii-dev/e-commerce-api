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
public class Product {
    private int id;
    private String productName;
    private String status; // 'append', 'available', 'unavailable'
    private float price;
    private String category;
    private String description;

    public Product(){

    }
    public Product(String productName, String status, float price, String category, String description) {
        this.productName = productName;
        this.status = status;
        this.price = price;
        this.category = category;
        this.description = description;
    }

}
