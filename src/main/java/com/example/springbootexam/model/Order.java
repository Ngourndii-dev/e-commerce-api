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

    public Order(int id, Date orderDate, Boolean status, int quantity, float totalPrice, Client client, Product product) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.client = client;
        this.product = product;
    }

    public Order(Date orderDate, Boolean status, int quantity, float totalPrice, Client client, Product product) {
        this.orderDate = orderDate;
        this.status = status;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.client = client;
        this.product = product;
    }
   public Order(){

   }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}























