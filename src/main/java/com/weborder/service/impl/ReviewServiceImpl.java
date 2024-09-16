package com.weborder.service.impl;

import com.weborder.dao.ReviewMapper;
import com.weborder.entity.Review;
import com.weborder.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private final ReviewMapper reviewMapper;
	
	@Autowired
	public ReviewServiceImpl(ReviewMapper reviewMapper) {
		this.reviewMapper = reviewMapper;
	}
	
	@Override
	public void createReview(Review review) {
		reviewMapper.insertReview(review);
	}
	
	@Override
	public List<Review> getReviewsByProductId(Integer productId) {
		return reviewMapper.getReviewsByProductId(productId);
	}
}
