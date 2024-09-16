package com.weborder.controller;

import com.weborder.entity.Review;
import com.weborder.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	
	private final ReviewService reviewService;
	
	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	// 添加评价
	@PostMapping
	public ResponseEntity<Void> createReview(@RequestBody Review review) {
		reviewService.createReview(review);
		return ResponseEntity.ok().build();
	}
	
	// 获取产品的所有评价
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Integer productId) {
		List<Review> reviews = reviewService.getReviewsByProductId(productId);
		return ResponseEntity.ok(reviews);
	}
	
	// 获取用户对某个产品的评价
	@GetMapping("/product/{productId}/user/{userId}")
	public ResponseEntity<Review> getUserReviewForProduct(@PathVariable Integer productId, @PathVariable Integer userId) {
		Review review = reviewService.getUserReviewForProduct(userId, productId);
		return ResponseEntity.ok(review);
	}
}
