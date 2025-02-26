package com.example.springbootexam.model;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class Promo {
    private int id;
    private Product product;
    private Date expirationDate;
    private String category;

public Promo(){

}
    public Promo(Product product, Date expirationDate, String category) {
        this.product = product;
        this.expirationDate = expirationDate;
        this.category = category;
    }

}
