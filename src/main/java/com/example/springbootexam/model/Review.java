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
public class Review {
    private int id;
    private String author;
    private Product product;
    private int rating;
    private String comment;

public Review(){

}
    public Review(String author, Product product, int rating, String comment) {
        this.author = author;
        this.product = product;
        this.rating = rating;
        this.comment = comment;
    }
}
