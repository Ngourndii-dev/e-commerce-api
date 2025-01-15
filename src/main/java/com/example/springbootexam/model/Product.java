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
    public Product(int id, String productName, String status, float price, String category, String description) {
        this.id = id;
        this.productName = productName;
        this.status = status;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public Product(String productName, String status, float price, String category, String description) {
        this.productName = productName;
        this.status = status;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
