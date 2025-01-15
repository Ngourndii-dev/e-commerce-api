package com.example.springbootexam.service;

import com.example.springbootexam.model.Review;
import com.example.springbootexam.repository.ReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewDAO reviewDAO;
    @Autowired
    public ReviewService (ReviewDAO reviewDAO){
        this.reviewDAO=reviewDAO;
    }
    public Review insert(Review review){return reviewDAO.insert(review);}
    public List<Review> findAll(){return reviewDAO.findAll();}
    public Review getById(int id){return reviewDAO.getById(id);}
    public void deleteById(int reviewId){reviewDAO.deleteById(reviewId);}
    public List<String> findAllComments(){return reviewDAO.findAllComments();}
}
