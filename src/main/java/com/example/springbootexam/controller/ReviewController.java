


package com.example.springbootexam.controller;

import com.example.springbootexam.model.Promo;
import com.example.springbootexam.model.Review;
import com.example.springbootexam.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> insertReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.insert(review));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getPromoById(@PathVariable int id) {
        return Optional.ofNullable(reviewService.getById(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.findAll());
    }

    @GetMapping("/comments")
    public ResponseEntity<List<String>> findAllComments() {
        return ResponseEntity.ok(reviewService.findAllComments());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
            reviewService.deleteById(id);
            return ResponseEntity.noContent().build();
    }
}






