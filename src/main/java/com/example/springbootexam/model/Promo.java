package com.example.springbootexam.model;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

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

    public Promo(int id, Product product, Date expirationDate, String category) {
        this.id = id;
        this.product = product;
        this.expirationDate = expirationDate;
        this.category = category;
    }
public Promo(){

}
    public Promo(Product product, Date expirationDate, String category) {
        this.product = product;
        this.expirationDate = expirationDate;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public java.sql.Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
