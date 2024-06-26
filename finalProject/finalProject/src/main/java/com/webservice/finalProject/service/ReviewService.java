package com.webservice.finalProject.service;

import com.webservice.finalProject.model.Review;
import com.webservice.finalProject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.getReviewById(id);
    }
/*
    public Review saveReview(Review review) {
        reviewRepository.saveReview(review);
        return review;
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteReview(id);
    }*/
}
